package com.tim.auth.service;

import com.tim.auth.vo.MenuTree;
import java.util.List;

/**
 * @author：tim
 * @date：20-3-1 上午11:50
 * @description：
 */
public interface MenuService {

  /**
   * 树形显示菜单
   */
  List<MenuTree> listTree();

  /**
   * 根据用户id查询菜单
   */
  List<MenuTree> listTreeUser(String userId);

  /**
   * 根据角色id查询菜单，返回菜单id集合
   */
  List<String> listMenuRole(String roleId);

}
