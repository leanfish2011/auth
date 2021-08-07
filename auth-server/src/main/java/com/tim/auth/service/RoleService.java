package com.tim.auth.service;

import com.tim.auth.sdk.vo.RoleAdd;
import com.tim.auth.sdk.vo.RoleMenuAdd;
import com.tim.auth.sdk.vo.RoleSearchReq;
import com.tim.auth.sdk.vo.RoleSearchResp;
import com.tim.auth.sdk.vo.RoleUpdate;
import com.tim.auth.sdk.vo.RoleUserAdd;
import java.util.List;

public interface RoleService {

  List<RoleSearchResp> search(RoleSearchReq roleSearchReq);

  Boolean isExist(String name);

  Boolean add(RoleAdd roleAdd);

  Boolean update(RoleUpdate roleUpdate);

  RoleSearchResp select(String id);

  Boolean delete(String id);

  /**
   * 角色增加用户。先删除角色下旧的全部用户，再增加
   *
   * @param roleUserAdd 全量用户
   */
  Boolean addUser(RoleUserAdd roleUserAdd);

  /**
   * 角色增加菜单。先删除角色下旧的全部菜单，再增加
   *
   * @param roleMenuAdd 全量菜单
   */
  Boolean addMenu(RoleMenuAdd roleMenuAdd);

}
