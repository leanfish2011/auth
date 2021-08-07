package com.tim.auth.service.impl;

import com.tim.auth.component.TokenManager;
import com.tim.auth.dao.RoleMenuMapper;
import com.tim.auth.po.RoleMenu;
import com.tim.auth.po.RoleMenuExample;
import com.tim.auth.service.RoleMenuService;
import com.tim.auth.vo.RoleMenuAdd;
import com.tim.auth.vo.RoleMenuDel;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：tim
 * @date：20-3-1 下午5:49
 * @description：
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

  @Autowired
  private RoleMenuMapper roleMenuMapper;

  @Autowired
  private TokenManager tokenManager;

  @Override
  public Boolean addMenu(RoleMenuAdd roleMenuAdd) {
    String roleId = roleMenuAdd.getRoleId();
    List<String> menuIdList = roleMenuAdd.getMenuIdList();
    for (String menuId : menuIdList) {
      RoleMenu roleMenu = new RoleMenu();
      roleMenu.setId(UUID.randomUUID().toString());
      roleMenu.setRoleid(roleId);
      roleMenu.setMenuid(menuId);
      roleMenu.setCreatorId(tokenManager.getUserId());

      roleMenuMapper.insertSelective(roleMenu);
    }

    return true;
  }

  @Override
  public Boolean deleteMenu(RoleMenuDel roleMenuDel) {
    String roleId = roleMenuDel.getRoleId();
    List<String> menuIdList = roleMenuDel.getMenuIdList();
    RoleMenuExample roleMenuExample = new RoleMenuExample();
    RoleMenuExample.Criteria criteria = roleMenuExample.createCriteria();
    criteria.andRoleidEqualTo(roleId);
    criteria.andMenuidIn(menuIdList);

    return roleMenuMapper.deleteByExample(roleMenuExample) > 0 ? true : false;
  }

  @Override
  public Boolean deleteRole(String roleId) {
    RoleMenuExample roleMenuExample = new RoleMenuExample();
    RoleMenuExample.Criteria criteria = roleMenuExample.createCriteria();
    criteria.andRoleidEqualTo(roleId);

    return roleMenuMapper.deleteByExample(roleMenuExample) > 0 ? true : false;
  }
}
