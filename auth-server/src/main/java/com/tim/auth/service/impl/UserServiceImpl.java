package com.tim.auth.service.impl;

import com.tim.auth.component.LoadResourceRole;
import com.tim.auth.component.TokenManager;
import com.tim.auth.exception.DuplicateException;
import com.tim.auth.exception.NotModifyException;
import com.tim.auth.sdk.constant.AuthConstant;
import com.tim.auth.service.RoleUserService;
import com.tim.auth.service.UserService;
import com.tim.auth.sdk.vo.RoleUserAdd;
import com.tim.auth.sdk.vo.UserAdd;
import com.tim.auth.sdk.vo.UserSearchReq;
import com.tim.auth.sdk.vo.UserSearchResp;
import com.tim.auth.sdk.vo.UserSearchRespData;
import com.tim.auth.sdk.vo.UserUpdate;
import java.util.ArrayList;
import java.util.List;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tim.auth.dao.UserMapper;
import com.tim.auth.po.User;
import com.tim.auth.po.UserExample;
import com.tim.auth.po.UserExample.Criteria;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private RoleUserService roleUserService;

  @Autowired
  private TokenManager tokenManager;

  @Autowired
  private LoadResourceRole loadResourceRole;

  @Override
  public UserSearchRespData search(UserSearchReq userSearchReq) {
    UserExample userExample = new UserExample();
    Criteria criteria = userExample.createCriteria();

    if (userSearchReq != null) {
      if (!StringUtils.isEmpty(userSearchReq.getName())) {
        criteria.andNameLike("%" + userSearchReq.getName() + "%");
      }

      if (!StringUtils.isEmpty(userSearchReq.getUserCode())) {
        criteria.andUsercodeLike("%" + userSearchReq.getUserCode() + "%");
      }

      if (userSearchReq.getBeginTime() != null && userSearchReq.getEndTime() != null) {
        criteria.andCreateTimeBetween(userSearchReq.getBeginTime(), userSearchReq.getEndTime());
      }

      if (!StringUtils.isEmpty(userSearchReq.getEmail())) {
        criteria.andEmailLike("%" + userSearchReq.getEmail() + "%");
      }

      Integer pageNo = userSearchReq.getPageNo();
      Integer pageSize = userSearchReq.getPageSize();
      if (pageNo != null && pageSize != null) {
        userExample.setLimitRange((pageNo - 1) * pageSize + "," + pageSize);
      }
    }

    userExample.setOrderByClause(" create_time desc");

    List<User> users = userMapper.selectByExample(userExample);
    List<UserSearchResp> userSearchRespList = new ArrayList<>();
    for (User user : users) {
      UserSearchResp response = new UserSearchResp();
      BeanUtils.copyProperties(user, response);

      userSearchRespList.add(response);
    }

    int allTotal = userMapper.countByExample(userExample);

    UserSearchRespData userSearchRespData = new UserSearchRespData();
    userSearchRespData.setAllTotal(allTotal);
    userSearchRespData.setCurrentTotal(userSearchRespList.size());
    userSearchRespData.setList(userSearchRespList);

    return userSearchRespData;
  }

  @Override
  public Boolean isExist(String userCode) {
    UserExample example = new UserExample();
    Criteria criteria = example.createCriteria();

    if (!StringUtils.isEmpty(userCode)) {
      criteria.andUsercodeEqualTo(userCode);
    }

    int count = userMapper.countByExample(example);

    return count > 0 ? true : false;
  }

  @Override
  public Boolean add(UserAdd userAdd) {
    String userCode = userAdd.getUserCode();
    boolean isExist = isExist(userCode);
    if (isExist) {
      log.warn("用户名已经存在：{}", userCode);
      throw new DuplicateException("该用户名已经存在！");
    }

    //插入用户
    User user = new User();
    BeanUtils.copyProperties(userAdd, user);
    String userId = UUID.randomUUID().toString();
    user.setId(userId);
    user.setCreatorId(tokenManager.getUserId());

    userMapper.insertSelective(user);

    //插入默认角色
    RoleUserAdd roleUserAdd = new RoleUserAdd();
    roleUserAdd.setRoleId(AuthConstant.ROLE_COMMON_ID);
    List<String> userIdList = new ArrayList<>(1);
    userIdList.add(userId);
    roleUserAdd.setUserIdList(userIdList);
    roleUserService.addUser(roleUserAdd);

    //刷新redis
    loadResourceRole.load();

    log.info("新增用户成功，用户名：{}", userCode);
    return true;
  }

  @Override
  public Boolean update(UserUpdate userUpdate) {
    User user = new User();
    BeanUtils.copyProperties(userUpdate, user);
    user.setModifierId(tokenManager.getUserId());
    userMapper.updateByPrimaryKeySelective(user);
    log.info("更新用户成功，用户名：{}", user.getUserCode());

    return true;
  }

  @Override
  public UserSearchResp select(String id) {
    User user = userMapper.selectByPrimaryKey(id);
    UserSearchResp userSearchResp = new UserSearchResp();
    BeanUtils.copyProperties(user, userSearchResp);

    return userSearchResp;
  }

  @Override
  @Transactional(rollbackFor = {RuntimeException.class, Error.class})
  public Boolean delete(String id) {
    if (id.equals(AuthConstant.USER_ADMIN_ID) || id.equals(AuthConstant.USER_COMMON_ID)) {
      log.warn("系统内置用户不能删除，id：{}", id);
      throw new NotModifyException("系统内置用户不能删除！");
    }

    //从角色用户表中删除
    roleUserService.deleteUser(id);

    //从用户表中删除
    userMapper.deleteByPrimaryKey(id);

    //刷新redis
    loadResourceRole.load();

    //redis中删除该用户
    //TODO 如果库中用户删除了，但是redis中token未删除，则该用户还可以操作
    //需要通过id查找用户token，删除token

    log.info("删除用户成功！");
    return true;
  }

  @Override
  public List<UserSearchResp> roleUser(String roleId) {
    List<User> users = userMapper.selectByRoleId(roleId);
    List<UserSearchResp> list = new ArrayList<>();
    for (User user : users) {
      UserSearchResp response = new UserSearchResp();
      BeanUtils.copyProperties(user, response);

      list.add(response);
    }

    return list;
  }

  @Override
  public User findOne(String userCode) {
    UserExample example = new UserExample();
    Criteria criteria = example.createCriteria();
    criteria.andUsercodeEqualTo(userCode);

    List<User> userList = userMapper.selectByExample(example);
    if (userList == null || userList.size() == 0) {
      return null;
    }

    return userList.get(0);
  }

}
