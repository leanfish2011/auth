package com.tim.auth.component;

import com.tim.auth.constant.AuthConstant;
import com.tim.auth.sdk.vo.LoginResp;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.tim.auth.sdk.vo.TokenModel;

/**
 * 通过 Redis 存储和验证 token 的实现类
 *
 * @author tim
 * @createTime 2018年5月10日-下午2:35:47
 */
@Component
public class TokenManager {

  @Autowired
  private RedisTemplate redisTemplate;

  @Autowired
  private RequestManager requestManager;

  @Value("${token.expires.minutes:2}")
  private int expiresMin;

  /**
   * 登录返回对象存入redis
   *
   * @param loginResp 登录返回对象
   */
  public void saveTokenModel(LoginResp loginResp) {
    TokenModel tokenModel = new TokenModel();
    tokenModel.setToken(loginResp.getToken());
    tokenModel.setLoginResp(loginResp);

    redisTemplate.opsForValue().set(loginResp.getToken(), tokenModel, expiresMin, TimeUnit.MINUTES);
  }

  /**
   * 根据token获取用户信息
   */
  public TokenModel getTokenModel(String token) {
    if (token == null) {
      return null;
    }

    Object object = redisTemplate.opsForValue().get(token);
    if (object == null) {
      return null;
    }

    // 如果验证成功，说明此用户进行了一次有效操作，延长 token 的过期时间
    redisTemplate.expire(token, expiresMin, TimeUnit.MINUTES);
    TokenModel tokenModel = (TokenModel) object;

    return tokenModel;
  }

  /**
   * 删除token
   */
  public void deleteToken(String token) {
    redisTemplate.delete(token);
  }

  /**
   * 检查token是否有效
   */
  public boolean checkToken(String token) {
    if (StringUtils.isEmpty(token)) {
      return false;
    }

    TokenModel tokenModel = getTokenModel(token);
    if (tokenModel == null) {
      return false;
    }

    return true;
  }

  /**
   * 获取当前用户id，获取不到则返回数据库初始化的管理员id
   *
   * @return 当前用户id或者管理员默认id
   */
  public String getUserId() {
    String token = requestManager.getAccessToken();
    TokenModel tokenModel = getTokenModel(token);

    if (tokenModel != null) {
      return tokenModel.getLoginResp().getUserId();
    }

    //超级管理员id
    return AuthConstant.USER_ADMIN_ID;
  }
}
