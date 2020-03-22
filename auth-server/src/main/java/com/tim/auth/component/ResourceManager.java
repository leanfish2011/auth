package com.tim.auth.component;

import com.tim.message.Message;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.tim.auth.ao.ResourceUser;
import com.tim.auth.ao.TokenModel;

@Component
public class ResourceManager {

  @Autowired
  private RedisTemplate redisTemplate;

  @Autowired
  private TokenManager tokenManager;

  /**
   * 加载资源权限到redis中
   */
  public void loadResource(List<ResourceUser> resource) {
    for (ResourceUser resourceUser : resource) {
      redisTemplate.opsForValue().set(resourceUser.getRequestPath(), resourceUser.getUserids());
    }
  }

  /**
   * 检查用户菜单权限
   *
   * @param requestPath 菜单路径
   * @param token 用户token
   * @return 是否拥有该菜单权限
   */
  public Message checkPermission(String requestPath, String token) {
    Object object = redisTemplate.opsForValue().get(requestPath);
    if (object == null) {
      return Message.error("资源不存在");
    }

    TokenModel model = tokenManager.getTokenModel(token);
    if (!object.toString().contains(model.getLoginResp().getUserId())) {
      return Message.error("无访问权限");
    }

    return Message.success();
  }

}
