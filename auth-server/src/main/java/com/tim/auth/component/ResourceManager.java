package com.tim.auth.component;

import com.tim.auth.exception.NoAccessException;
import com.tim.auth.exception.NotFoundException;
import com.tim.exception.type.CommonException;
import com.tim.message.Message;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.tim.auth.ao.ResourceRole;
import com.tim.auth.sdk.vo.TokenModel;

@Component
public class ResourceManager {

  @Autowired
  private RedisTemplate redisTemplate;

  @Autowired
  private TokenManager tokenManager;

  /**
   * 加载资源权限到redis中
   */
  public void loadResource(List<ResourceRole> resource) {
    for (ResourceRole resourceRole : resource) {
      redisTemplate.opsForValue().set(resourceRole.getRequestPath(), resourceRole.getRoleIds());
    }
  }

  /**
   * 检查用户菜单权限
   *
   * @param requestPath 菜单路径
   * @param token 用户token
   * @return 是否拥有该菜单权限
   */
  public Boolean checkPermission(String requestPath, String method, String token) {
    String checkUri = requestPath;
    //因为接口是restful，delete、get等接口需要截掉id，获取真正的路径。本系统id都带有-
    String id = requestPath.substring(requestPath.lastIndexOf("/") + 1);
    if (StringUtils.isNotEmpty(id) && id.contains("-")) {
      checkUri = requestPath.substring(0, requestPath.lastIndexOf("/"));
    }

    Object urlRoleIdsObj = redisTemplate.opsForValue().get(checkUri);
    if (urlRoleIdsObj == null) {
      throw new NotFoundException("资源不存在");
    }

    //鉴权：判断url需要的角色，是否在用户所属角色中
    TokenModel model = tokenManager.getTokenModel(token);
    String roleIds = model.getLoginResp().getRoleIds();
    Set<String> userRoleSet = new HashSet<>(Arrays.asList(roleIds.split(",")));
    userRoleSet.retainAll(Arrays.asList(urlRoleIdsObj.toString().split(",")));

    if (userRoleSet.size() == 0) {
      throw new NoAccessException("无访问权限");
    }

    return true;
  }

}
