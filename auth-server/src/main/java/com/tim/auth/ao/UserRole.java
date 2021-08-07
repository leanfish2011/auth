package com.tim.auth.ao;

import lombok.Data;

/**
 * @author：tim
 * @date：20-5-7 下午10:36
 * @description：
 */
@Data
public class UserRole {

  /**
   * 用户id
   */
  private String userId;

  /**
   * 角色id集合
   */
  private String roleIds;
}
