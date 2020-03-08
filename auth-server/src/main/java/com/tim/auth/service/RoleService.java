package com.tim.auth.service;

import com.tim.auth.vo.RoleAdd;
import com.tim.auth.vo.RoleMenuAdd;
import com.tim.auth.vo.RoleMenuDel;
import com.tim.auth.vo.RoleSearchReq;
import com.tim.auth.vo.RoleSearchResp;
import com.tim.auth.vo.RoleUpdate;
import com.tim.auth.vo.RoleUserAdd;
import com.tim.auth.vo.RoleUserDel;
import java.util.List;

public interface RoleService {

  List<RoleSearchResp> search(RoleSearchReq roleSearchReq);

  boolean isExist(String name);

  boolean add(RoleAdd roleAdd);

  boolean update(RoleUpdate roleUpdate);

  RoleSearchResp select(String id);

  boolean delete(String id);

  /**
   * 角色增加用户
   */
  boolean addUser(RoleUserAdd roleUserAdd);

  /**
   * 角色增加菜单
   */
  boolean addMenu(RoleMenuAdd roleMenuAdd);

  /**
   * 角色删除用户
   */
  boolean deleteUser(RoleUserDel roleUserDel);

  /**
   * 角色删除菜单
   */
  boolean deleteMenu(RoleMenuDel roleMenuDel);
}
