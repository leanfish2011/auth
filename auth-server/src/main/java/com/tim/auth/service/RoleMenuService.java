package com.tim.auth.service;

import com.tim.auth.sdk.vo.RoleMenuAdd;
import com.tim.auth.sdk.vo.RoleMenuDel;

public interface RoleMenuService {

  /**
   * 角色增加菜单
   */
  Boolean addMenu(RoleMenuAdd roleMenuAdd);

  /**
   * 角色删除菜单
   */
  Boolean deleteMenu(RoleMenuDel roleMenuDel);

  /**
   * 根据角色id删除
   */
  Boolean deleteRole(String roleId);
}
