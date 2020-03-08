package com.tim.auth.service;

import com.tim.auth.vo.RoleUserAdd;
import com.tim.auth.vo.RoleUserDel;

/**
 * @author：tim
 * @date：20-3-1 下午5:54
 * @description：
 */
public interface RoleUserService {

  /**
   * 角色增加用户
   */
  boolean addUser(RoleUserAdd roleUserAdd);

  /**
   * 角色删除用户
   */
  boolean deleteUser(RoleUserDel roleUserDel);

  /**
   * 根据用户id删除
   */
  boolean deleteUser(String userId);

  /**
   * 根据角色id删除
   */
  boolean deleteRole(String roleId);
}
