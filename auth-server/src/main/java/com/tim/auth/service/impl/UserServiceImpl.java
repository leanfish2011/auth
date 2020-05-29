package com.tim.auth.service.impl;

import com.tim.auth.component.LoadResourceRole;
import com.tim.auth.component.TokenManager;
import com.tim.auth.sdk.constant.AuthConstant;
import com.tim.auth.service.RoleUserService;
import com.tim.auth.service.UserService;
import com.tim.auth.vo.RoleUserAdd;
import com.tim.auth.vo.UserAdd;
import com.tim.auth.vo.UserSearchReq;
import com.tim.auth.vo.UserSearchResp;
import com.tim.auth.vo.UserUpdate;
import com.tim.message.Message;
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
  public List<UserSearchResp> search(UserSearchReq userSearchReq) {
    UserExample example = new UserExample();
    Criteria criteria = example.createCriteria();

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

    example.setOrderByClause(" create_time asc");

    List<User> users = userMapper.selectByExample(example);
    List<UserSearchResp> list = new ArrayList<UserSearchResp>();
    for (User user : users) {
      UserSearchResp response = new UserSearchResp();
      BeanUtils.copyProperties(user, response);

      list.add(response);
    }

    return list;
  }

  @Override
  public boolean isExist(String userCode) {
    UserExample example = new UserExample();
    Criteria criteria = example.createCriteria();

    if (!StringUtils.isEmpty(userCode)) {
      criteria.andUsercodeEqualTo(userCode);
    }

    int count = userMapper.countByExample(example);

    return count > 0 ? true : false;
  }

  @Override
  public Message add(UserAdd userAdd) {
    String userCode = userAdd.getUserCode();
    boolean isExist = isExist(userCode);
    if (isExist) {
      log.warn("用户名已经存在：{}", userCode);
      return Message.error("该用户名已经存在！");
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

    return Message.success("新增用户成功！");
  }

  @Override
  public Message update(UserUpdate userUpdate) {
    User user = new User();
    BeanUtils.copyProperties(userUpdate, user);
    user.setModifierId(tokenManager.getUserId());

    int result = userMapper.updateByPrimaryKeySelective(user);
    if (result == 1) {
      return Message.success();
    }

    return Message.error();
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
  public boolean delete(String id) {
    //从角色用户表中删除
    roleUserService.deleteUser(id);

    //从用户表中删除
    userMapper.deleteByPrimaryKey(id);

    //刷新redis
    loadResourceRole.load();

    //redis中删除该用户
    //TODO 如果库中用户删除了，但是redis中token未删除，则该用户还可以操作

    return true;
  }

  @Override
  public List<UserSearchResp> roleUser(String roleId) {
    List<User> users = userMapper.selectByRoleId(roleId);
    List<UserSearchResp> list = new ArrayList<UserSearchResp>();
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
