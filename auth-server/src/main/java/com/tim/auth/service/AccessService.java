package com.tim.auth.service;

import com.tim.auth.sdk.vo.TokenModel;
import com.tim.auth.sdk.vo.LoginReq;
import com.tim.auth.sdk.vo.LoginResp;
import com.tim.auth.sdk.vo.RegisterReq;
import com.tim.auth.sdk.vo.UpdatePwdReq;
import java.util.List;
import com.tim.auth.ao.ResourceRole;

public interface AccessService {

  /**
   * 用户名密码登录
   *
   * @param loginReq 登录请求参数：用户名、密码
   * @return 登录成功后信息
   */
  LoginResp login(LoginReq loginReq);

  Boolean logout();

  TokenModel profile();

  Boolean register(RegisterReq registerReq);

  Boolean check();

  Boolean checkPermission(String uri, String method);

  /**
   * 加载权限和角色
   */
  List<ResourceRole> loadRequestResouce();

  Boolean updatePassword(UpdatePwdReq updatePwdReq);

  /**
   * 使用github登录
   *
   * @param code 第一次请求github返回的code
   * @return 登录结果
   */
  LoginResp githubLogin(String code) throws Exception;
}
