package com.tim.auth.service;

import com.tim.auth.ao.TokenModel;
import com.tim.auth.vo.LoginReq;
import com.tim.auth.vo.LoginResp;
import com.tim.auth.vo.RegisterReq;
import com.tim.message.Message;
import java.util.List;
import com.tim.auth.ao.ResourceUser;

public interface AccessService {

  Message<LoginResp> login(LoginReq loginReq);

  Message logout();

  Message<TokenModel> profile();

  Message register(RegisterReq registerReq);

  Message check();

  Message checkPermission(String uri);

  /**
   * 加载权限和角色
   */
  List<ResourceUser> loadRequestResouce();

}
