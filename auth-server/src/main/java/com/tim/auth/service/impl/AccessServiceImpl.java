package com.tim.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.tim.auth.ao.GithubUser;
import com.tim.auth.ao.UserRole;
import com.tim.auth.component.LoadResourceRole;
import com.tim.auth.constant.GitHubLoginConstant;
import com.tim.auth.constant.UserInfoConstant;
import com.tim.auth.sdk.vo.TokenModel;
import com.tim.auth.common.AuthCode;
import com.tim.auth.component.RequestManager;
import com.tim.auth.component.ResourceManager;
import com.tim.auth.component.TokenManager;
import com.tim.auth.sdk.constant.AuthConstant;
import com.tim.auth.po.User;
import com.tim.auth.service.AccessService;
import com.tim.auth.service.RoleUserService;
import com.tim.auth.service.UserService;
import com.tim.auth.util.HttpClientUtils;
import com.tim.auth.util.MapConvert;
import com.tim.auth.vo.LoginReq;
import com.tim.auth.sdk.vo.LoginResp;
import com.tim.auth.vo.RegisterReq;
import com.tim.auth.vo.RoleUserAdd;
import com.tim.auth.vo.UpdatePwdReq;
import com.tim.auth.vo.UserSearchResp;
import com.tim.message.MainCode;
import com.tim.message.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tim.auth.ao.ResourceRole;
import com.tim.auth.dao.UserMapper;

@Service
@Slf4j
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

  @Autowired
  private LoadResourceRole loadResourceRole;

  @Override
  public Message<LoginResp> login(LoginReq loginReq) {
    User user = userService.findOne(loginReq.getUserCode());
    if (user == null) {
      return Message.error("用户不存在！");
    }

    if (!user.getPassword().equals(loginReq.getPassword())) {
      return Message.error("用户名或者密码错误！");
    }

    LoginResp loginResp = this.afterLogin(user);

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

    //TODO 与后端新增用户代码重复

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

    //刷新redis
    loadResourceRole.load();

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

    return Message.success();
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
  public List<ResourceRole> loadRequestResouce() {
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

  @Override
  public Message<LoginResp> githubLogin(String code) throws Exception {
    GithubUser githubUser = this.queryUser(code);

    boolean isExist = userService.isExist(githubUser.getLogin());
    if (isExist) {
      LoginResp loginResp = this.login(githubUser.getLogin());
      if (loginResp != null) {
        return Message.success(loginResp);
      }
    }

    //注册
    RegisterReq registerReq = new RegisterReq();
    registerReq.setUserCode(githubUser.getLogin());
    registerReq.setPassword(UserInfoConstant.USER_PASSWORD);
    registerReq.setName(githubUser.getName());
    registerReq.setEmail(githubUser.getEmail());

    Message message = this.register(registerReq);
    if (message.getCode().equals(MainCode.SUCCESS)) {
      LoginResp loginResp = this.login(githubUser.getLogin());
      if (loginResp != null) {
        return Message.success(loginResp);
      }
    }

    return Message.error("GitHub登录失败");
  }

  /**
   * 根据github授权码，获取用户信息
   *
   * @param code 授权码
   * @return github用户信息
   * @throws Exception 网络异常
   */
  private GithubUser queryUser(String code) throws Exception {
    String tokenUrl = GitHubLoginConstant.TOKEN_URL.replace("CODE", code);
    //使用code拿到包含token信息字符串
    String tokenInfo = HttpClientUtils.doGet(tokenUrl, null);
    String token = MapConvert.getMap(tokenInfo).get("access_token");

    //根据token发送请求获取登录人的信息，通过令牌去获得用户信息
    // 经过测试，url和header都需要带上token
    String userInfoUrl = GitHubLoginConstant.USER_INFO_URL.replace("TOKEN", token);
    String userInfo = HttpClientUtils.doGet(userInfoUrl, "token " + token);

    return JSON.parseObject(userInfo, GithubUser.class);
  }

  /**
   * 使用用户名登录
   *
   * @param userCode 用户名
   * @return 登录成功用户信息
   */
  private LoginResp login(String userCode) {
    User user = userService.findOne(userCode);
    if (user == null) {
      return null;
    }

    return afterLogin(user);
  }

  /**
   * 登录成功后处理
   *
   * @param user 登录成功的用户
   * @return 登录成功后信息
   */
  private LoginResp afterLogin(User user) {
    LoginResp loginResp = new LoginResp();
    loginResp.setToken(UUID.randomUUID().toString());
    loginResp.setUserCode(user.getUserCode());
    loginResp.setUserId(user.getId());
    loginResp.setName(user.getName());

    UserRole userRole = userMapper.selectUserRole(user.getId());
    loginResp.setRoleIds(userRole.getRoleIds());

    //redis存储
    tokenManager.saveTokenModel(loginResp);

    return loginResp;
  }

}
