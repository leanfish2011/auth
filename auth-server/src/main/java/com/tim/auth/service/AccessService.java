package com.tim.auth.service;

import com.tim.auth.sdk.vo.TokenModel;
import com.tim.auth.vo.LoginReq;
import com.tim.auth.sdk.vo.LoginResp;
import com.tim.auth.vo.RegisterReq;
import com.tim.auth.vo.UpdatePwdReq;
import com.tim.message.Message;
import java.util.List;
import com.tim.auth.ao.ResourceUser;

public interface AccessService {

  Message<LoginResp> login(LoginReq loginReq);

  Message logout();

  Message<TokenModel> profile();

  Message register(RegisterReq registerReq);

  Message check();

  Message checkPermission(String uri, String method);

  /**
   * 加载权限和角色
   */
  List<ResourceUser> loadRequestResouce();

  Message updatePassword(UpdatePwdReq updatePwdReq);

  /**
   * 使用github登录
   *
   * @param code 第一次请求github返回的code
   * @return 登录结果
   */
  Message<LoginResp> githubLogin(String code) throws Exception;
}
