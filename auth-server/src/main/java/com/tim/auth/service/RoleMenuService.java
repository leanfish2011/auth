package com.tim.auth.service;

import com.tim.auth.vo.RoleMenuAdd;
import com.tim.auth.vo.RoleMenuDel;

public interface RoleMenuService {

  /**
   * 角色增加菜单
   */
  boolean addMenu(RoleMenuAdd roleMenuAdd);

  /**
   * 角色删除菜单
   */
  boolean deleteMenu(RoleMenuDel roleMenuDel);

  /**
   * 根据角色id删除
   */
  boolean deleteRole(String roleId);
}
