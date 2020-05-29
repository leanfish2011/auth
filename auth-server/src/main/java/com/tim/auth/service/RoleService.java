package com.tim.auth.service;

import com.tim.auth.vo.RoleAdd;
import com.tim.auth.vo.RoleMenuAdd;
import com.tim.auth.vo.RoleMenuDel;
import com.tim.auth.vo.RoleSearchReq;
import com.tim.auth.vo.RoleSearchResp;
import com.tim.auth.vo.RoleUpdate;
import com.tim.auth.vo.RoleUserAdd;
import com.tim.auth.vo.RoleUserDel;
import com.tim.message.Message;
import java.util.List;

public interface RoleService {

  List<RoleSearchResp> search(RoleSearchReq roleSearchReq);

  boolean isExist(String name);

  Message add(RoleAdd roleAdd);

  Message update(RoleUpdate roleUpdate);

  RoleSearchResp select(String id);

  Message delete(String id);

  /**
   * 角色增加用户
   */
  Message addUser(RoleUserAdd roleUserAdd);

  /**
   * 角色增加菜单
   */
  Message addMenu(RoleMenuAdd roleMenuAdd);

  /**
   * 角色删除用户
   */
  Message deleteUser(RoleUserDel roleUserDel);

  /**
   * 角色删除菜单
   */
  boolean deleteMenu(RoleMenuDel roleMenuDel);
}
