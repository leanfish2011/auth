package com.tim.auth.service.impl;

import com.tim.auth.ao.TokenModel;
import com.tim.auth.component.TokenManager;
import com.tim.auth.po.User;
import com.tim.auth.po.UserExample;
import com.tim.auth.po.UserExample.Criteria;
import com.tim.auth.service.AccessService;
import com.tim.auth.vo.LoginReq;
import com.tim.auth.vo.LoginResp;
import com.tim.message.Message;
import java.util.List;
import java.util.UUID;
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
    loginResp.setUserCode(user.getUsercode());
    loginResp.setUserId(user.getId());

    //redis存储
    tokenManager.saveTokenModel(loginResp);

    return Message.success(loginResp);
  }

  @Override
  public Message logout(String token) {
    tokenManager.deleteToken(token);
    return Message.success();
  }

  @Override
  public Message<TokenModel> profile(String token) {
    TokenModel tokenModel = tokenManager.getTokenModel(token);
    return Message.success(tokenModel);
  }

  @Override
  public Message check(String token) {
    boolean isExist = tokenManager.checkToken(token);
    if (!isExist) {
      return Message.error("token失效！");
    }

    return Message.success();
  }

  @Override
  public List<ResourceUser> loadRequestResouce() {
    return userMapper.loadRequestResouce();
  }

}
