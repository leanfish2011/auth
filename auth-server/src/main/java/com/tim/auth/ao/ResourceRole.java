package com.tim.auth.ao;

import lombok.Data;

/**
 * 请求路径与角色关系
 */
@Data
public class ResourceRole {

  /**
   * 资源请求路径
   */
  private String requestPath;

  /**
   * 拥有资源角色id集合
   */
  private String roleIds;
}
