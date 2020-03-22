package com.tim.auth.service.impl;

import com.tim.auth.dao.MenuMapper;
import com.tim.auth.po.Menu;
import com.tim.auth.po.MenuExample;
import com.tim.auth.po.MenuExample.Criteria;
import com.tim.auth.service.MenuService;
import com.tim.auth.vo.MenuResp;
import com.tim.auth.vo.MenuTree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：tim
 * @date：20-3-1 上午11:52
 * @description：
 */
@Service
public class MenuServiceImpl implements MenuService {

  @Autowired
  private MenuMapper menuMapper;

  @Override
  public List<MenuTree> listTree() {
    MenuExample menuExample = new MenuExample();
    menuExample.setOrderByClause(" sort_num asc");

    List<Menu> menuList = menuMapper.selectByExample(menuExample);

    return getMenuTreeList(menuList);
  }

  @Override
  public List<MenuTree> listTreeUser(String userId) {
    List<Menu> menuList = menuMapper.selectByUserId(userId);

    return getMenuTreeList(menuList);
  }

  @Override
  public List<MenuTree> listTreeRole(String roleId) {
    List<Menu> menuList = menuMapper.selectByRoleId(roleId);

    return getMenuTreeList(menuList);
  }

  /**
   * 根据菜单列表，生成菜单级别列表
   *
   * @param menuList 菜单列表
   */
  private List<MenuTree> getMenuTreeList(List<Menu> menuList) {
    // 存储一级菜单
    List<MenuTree> menuTreeList = new ArrayList<>();

    // 将菜单构建成树
    Map<String, MenuTree> temp = new HashMap<>();// 以id和菜单为主键
    for (Menu menu : menuList) {
      MenuResp menuResp = new MenuResp();
      BeanUtils.copyProperties(menu, menuResp);
      MenuTree menuTree = new MenuTree(menuResp);

      if (menu.getParentId() == null) {//一级菜单为总的根，parentid=null
        menuTreeList.add(menuTree);
      } else {
        MenuTree parent = temp.get(menu.getParentId());// 通过parentid找到父节点
        if (parent != null) {
          parent.getChildren().add(menuTree);// 当前则为子，加上子
        }
      }

      // 放入map 中， 已备子节点索引
      temp.put(menu.getId(), menuTree);
    }

    return menuTreeList;
  }

}
