package com.tim.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.tim.auth.ao.GithubUser;
import com.tim.auth.ao.UserRole;
import com.tim.auth.component.LoadResourceRole;
import com.tim.auth.constant.GitHubLoginConstant;
import com.tim.auth.constant.UserInfoConstant;
import com.tim.auth.exception.BadParameterException;
import com.tim.auth.exception.DuplicateException;
import com.tim.auth.exception.InvalidTokenException;
import com.tim.auth.exception.NotFoundException;
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
import com.tim.auth.sdk.vo.LoginReq;
import com.tim.auth.sdk.vo.LoginResp;
import com.tim.auth.sdk.vo.RegisterReq;
import com.tim.auth.sdk.vo.RoleUserAdd;
import com.tim.auth.sdk.vo.UpdatePwdReq;
import com.tim.auth.sdk.vo.UserSearchResp;
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
  public LoginResp login(LoginReq loginReq) {
    String userCode = loginReq.getUserCode();
    User user = userService.findOne(userCode);
    if (user == null) {
      log.warn("用户不存在，用户名：{}", userCode);
      throw new NotFoundException("用户不存在！");
    }

    String password = loginReq.getPassword();
    if (!user.getPassword().equals(password)) {
      log.warn("用户名或者密码错误，用户名：{}，密码：{}", userCode, password);
      throw new BadParameterException("用户名或者密码错误！");
    }

    return this.afterLogin(user);
  }

  @Override
  public Boolean logout() {
    String token = requestManager.getAccessToken();
    if (StringUtils.isEmpty(token)) {
      log.warn("token为空");
      throw new NotFoundException("token为空！");
    }

    tokenManager.deleteToken(token);
    return true;
  }

  @Override
  public TokenModel profile() {
    String token = requestManager.getAccessToken();
    if (StringUtils.isEmpty(token)) {
      log.warn("token为空");
      throw new NotFoundException("token为空！");
    }

    TokenModel tokenModel = tokenManager.getTokenModel(token);
    if (tokenModel == null) {
      log.warn("未找到用户信息，token:{}", token);
      throw new NotFoundException("未找到用户信息！");
    }

    return tokenModel;
  }

  @Override
  public Boolean register(RegisterReq registerReq) {
    String userCode = registerReq.getUserCode();
    boolean isExist = userService.isExist(userCode);
    if (isExist) {
      log.warn("该用户名已经存在，用户名:{}", userCode);
      throw new DuplicateException("该用户名已经存在！");
    }

    //TODO 与后端新增用户代码重复

    //插入用户
    User user = new User();
    BeanUtils.copyProperties(registerReq, user);
    String userId = UUID.randomUUID().toString();
    user.setId(userId);
    user.setCreatorId(userCode);

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

    return true;
  }

  @Override
  public Boolean check() {
    String token = requestManager.getAccessToken();
    if (StringUtils.isEmpty(token)) {
      log.warn("token为空");
      throw new NotFoundException("token为空！");
    }

    boolean isExist = tokenManager.checkToken(token);
    if (!isExist) {
      log.warn("token无效，为空或不存在，token:{}", token);
      throw new InvalidTokenException(AuthCode.INVALIDTOKEN_MSG);
    }

    return true;
  }

  @Override
  public Boolean checkPermission(String uri, String method) {
    if (StringUtils.isEmpty(uri)) {
      log.warn("请求路径为空");
      throw new NotFoundException("请求路径为空！");
    }

    String token = requestManager.getAccessToken();
    boolean isExist = tokenManager.checkToken(token);
    if (!isExist) {
      log.warn("token无效，为空或不存在，token:{}", token);
      throw new InvalidTokenException(AuthCode.INVALIDTOKEN_MSG);
    }

    return resourceManager.checkPermission(uri, method, token);
  }

  @Override
  public List<ResourceRole> loadRequestResouce() {
    return userMapper.loadRequestResouce();
  }

  @Override
  public Boolean updatePassword(UpdatePwdReq updatePwdReq) {
    String id = updatePwdReq.getId();
    UserSearchResp userSearchResp = userService.select(id);
    if (userSearchResp == null) {
      log.warn("用户不存在，用户id:{}", id);
      throw new NotFoundException("用户不存在！");
    }

    String inputOldPwd = updatePwdReq.getOldPassword();
    String dbOldPwd = userSearchResp.getPassword();
    if (!inputOldPwd.equals(dbOldPwd)) {
      log.warn("旧密码错误，输入的旧密码:{}，数据库中的旧密码：{}", inputOldPwd, dbOldPwd);
      throw new BadParameterException("旧密码错误！");
    }

    String newPwd = updatePwdReq.getNewPassword();
    if (inputOldPwd.equals(newPwd)) {
      log.warn("新密码和旧密码相同，新密码：{}，旧密码：", newPwd, inputOldPwd);
      throw new BadParameterException("新密码和旧密码相同！");
    }

    User user = new User();
    user.setId(id);
    user.setPassword(newPwd);
    user.setModifierId(tokenManager.getUserId());

    userMapper.updateByPrimaryKeySelective(user);

    return true;
  }

  @Override
  public LoginResp githubLogin(String code) throws Exception {
    log.info("github返回授权码：" + code);
    GithubUser githubUser = this.queryUser(code);

    boolean isExist = userService.isExist(githubUser.getLogin());
    if (isExist) {
      LoginResp loginResp = this.login(githubUser.getLogin());
      if (loginResp != null) {
        return loginResp;
      }
    }

    //注册
    RegisterReq registerReq = new RegisterReq();
    registerReq.setUserCode(githubUser.getLogin());
    registerReq.setPassword(UserInfoConstant.USER_PASSWORD);
    registerReq.setName(githubUser.getName());
    registerReq.setEmail(githubUser.getEmail());
    registerReq.setPhotourl(githubUser.getAvatarUrl());

    boolean isSuccess = this.register(registerReq);
    if (isSuccess) {
      LoginResp loginResp = this.login(githubUser.getLogin());
      if (loginResp != null) {
        return loginResp;
      }
    }

    throw new BadParameterException("GitHub登录失败");
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
      log.warn("用户不存在，用户名：{}", userCode);
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
    loginResp.setPhotourl(user.getPhotourl());

    UserRole userRole = userMapper.selectUserRole(user.getId());
    loginResp.setRoleIds(userRole.getRoleIds());

    //redis存储
    tokenManager.saveTokenModel(loginResp);

    return loginResp;
  }

}
