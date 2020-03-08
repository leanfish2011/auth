package com.tim.auth.ao;

import lombok.Data;

@Data
public class ResourceUser {

  /**
   * 资源请求路径
   */
  private String requestPath;

  /**
   * 拥有资源用户id集合
   */
  private String userids;
}
