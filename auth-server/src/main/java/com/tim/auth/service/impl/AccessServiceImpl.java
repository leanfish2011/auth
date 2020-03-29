package com.tim.auth.service.impl;

import com.tim.auth.ao.TokenModel;
import com.tim.auth.common.AuthCode;
import com.tim.auth.component.RequestManager;
import com.tim.auth.component.ResourceManager;
import com.tim.auth.component.TokenManager;
import com.tim.auth.constant.AuthConstant;
import com.tim.auth.po.User;
import com.tim.auth.po.UserExample;
import com.tim.auth.po.UserExample.Criteria;
import com.tim.auth.service.AccessService;
import com.tim.auth.service.RoleUserService;
import com.tim.auth.service.UserService;
import com.tim.auth.vo.LoginReq;
import com.tim.auth.vo.LoginResp;
import com.tim.auth.vo.RegisterReq;
import com.tim.auth.vo.RoleUserAdd;
import com.tim.auth.vo.UpdatePwdReq;
import com.tim.auth.vo.UserSearchResp;
import com.tim.message.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tim.auth.ao.ResourceUser;
import com.tim.auth.dao.UserMapper;

@Service
public class AccessServiceImpl implements AccessService {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private TokenManager tokenManager;

  @Autowired
  private ResourceManager resourceManager;

  @Autowired
  private RequestManager requestManager;

  @Autowired
  private UserService userService;

  @Autowired
  private RoleUserService roleUserService;

  @Override
  public Message<LoginResp> login(LoginReq loginReq) {
    UserExample example = new UserExample();
    Criteria criteria = example.createCriteria();
    criteria.andUsercodeEqualTo(loginReq.getUserCode());

    List<User> userList = userMapper.selectByExample(example);
    if (userList == null || userList.size() == 0) {
      return Message.error("用户不存在！");
    }

    User user = userList.get(0);
    if (!user.getPassword().equals(loginReq.getPassword())) {
      return Message.error("用户名或者密码错误！");
    }

    LoginResp loginResp = new LoginResp();
    loginResp.setToken(UUID.randomUUID().toString());
    loginResp.setUserCode(user.getUserCode());
    loginResp.setUserId(user.getId());
    loginResp.setName(user.getName());

    //redis存储
    tokenManager.saveTokenModel(loginResp);

    return Message.success(loginResp);
  }

  @Override
  public Message logout() {
    String token = requestManager.getAccessToken();
    if (StringUtils.isEmpty(token)) {
      return Message.error("token为空！");
    }

    tokenManager.deleteToken(token);
    return Message.success();
  }

  @Override
  public Message<TokenModel> profile() {
    String token = requestManager.getAccessToken();
    if (StringUtils.isEmpty(token)) {
      return Message.error("token为空！");
    }

    TokenModel tokenModel = tokenManager.getTokenModel(token);
    if (tokenModel == null) {
      return Message.error("未找到用户信息！");
    }

    return Message.success(tokenModel);
  }

  @Override
  public Message register(RegisterReq registerReq) {
    boolean isExist = userService.isExist(registerReq.getUserCode());
    if (isExist) {
      return Message.error("该用户名已经存在！");
    }

    //插入用户
    User user = new User();
    BeanUtils.copyProperties(registerReq, user);
    String userId = UUID.randomUUID().toString();
    user.setId(userId);
    user.setCreatorId(registerReq.getUserCode());

    userMapper.insertSelective(user);

    //插入默认角色
    RoleUserAdd roleUserAdd = new RoleUserAdd();
    roleUserAdd.setRoleId(AuthConstant.ROLE_COMMON_ID);
    List<String> userIdList = new ArrayList<>(1);
    userIdList.add(userId);
    roleUserAdd.setUserIdList(userIdList);
    roleUserService.addUser(roleUserAdd);

    return Message.success();
  }

  @Override
  public Message check() {
    String token = requestManager.getAccessToken();
    if (StringUtils.isEmpty(token)) {
      return Message.error("token为空！");
    }

    boolean isExist = tokenManager.checkToken(token);
    if (!isExist) {
      return new Message(AuthCode.INVALIDTOKEN, AuthCode.INVALIDTOKEN_MSG);
    }

    return Message.success(true);
  }

  @Override
  public Message checkPermission(String uri, String method) {
    if (StringUtils.isEmpty(uri)) {
      return Message.error("请求路径为空！");
    }

    String token = requestManager.getAccessToken();
    boolean isExist = tokenManager.checkToken(token);
    if (!isExist) {
      return Message.error("token无效！");
    }

    return resourceManager.checkPermission(uri, method, token);
  }

  @Override
  public List<ResourceUser> loadRequestResouce() {
    return userMapper.loadRequestResouce();
  }

  @Override
  public Message updatePassword(UpdatePwdReq updatePwdReq) {
    String id = updatePwdReq.getId();
    String oldPwd = updatePwdReq.getOldPassword();
    String newPwd = updatePwdReq.getNewPassword();
    UserSearchResp userSearchResp = userService.select(id);
    if (userSearchResp == null) {
      return Message.error("用户不存在！");
    }

    if (!oldPwd.equals(userSearchResp.getPassword())) {
      return Message.error("旧密码错误！");
    }

    if (oldPwd.equals(newPwd)) {
      return Message.error("新密码和旧密码相同！");
    }

    User user = new User();
    user.setId(id);
    user.setPassword(newPwd);
    user.setModifierId(tokenManager.getUserId());

    int result = userMapper.updateByPrimaryKeySelective(user);
    if (result == 1) {
      return Message.success();
    }

    return Message.error();
  }

}
