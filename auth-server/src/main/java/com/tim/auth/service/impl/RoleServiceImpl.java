package com.tim.auth.service.impl;

import com.tim.auth.component.TokenManager;
import com.tim.auth.dao.RoleMapper;
import com.tim.auth.po.Role;
import com.tim.auth.po.RoleExample;
import com.tim.auth.po.RoleExample.Criteria;
import com.tim.auth.service.RoleMenuService;
import com.tim.auth.service.RoleService;
import com.tim.auth.service.RoleUserService;
import com.tim.auth.vo.RoleAdd;
import com.tim.auth.vo.RoleMenuAdd;
import com.tim.auth.vo.RoleMenuDel;
import com.tim.auth.vo.RoleSearchReq;
import com.tim.auth.vo.RoleSearchResp;
import com.tim.auth.vo.RoleUpdate;
import com.tim.auth.vo.RoleUserAdd;
import com.tim.auth.vo.RoleUserDel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author：tim
 * @date：20-2-29 下午10:24
 * @description：
 */
@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleMapper roleMapper;

  @Autowired
  private RoleMenuService roleMenuService;

  @Autowired
  private RoleUserService roleUserService;

  @Autowired
  private TokenManager tokenManager;

  @Override
  public List<RoleSearchResp> search(RoleSearchReq roleSearchReq) {
    RoleExample example = new RoleExample();
    Criteria criteria = example.createCriteria();

    if (!StringUtils.isEmpty(roleSearchReq.getName())) {
      criteria.andNameLike("%" + roleSearchReq.getName() + "%");
    }

    example.setOrderByClause(" create_time asc");

    List<Role> roles = roleMapper.selectByExample(example);
    List<RoleSearchResp> list = new ArrayList<>();
    for (Role role : roles) {
      RoleSearchResp roleSearchResp = new RoleSearchResp();
      BeanUtils.copyProperties(role, roleSearchResp);

      list.add(roleSearchResp);
    }

    return list;
  }

  @Override
  public boolean isExist(String name) {
    RoleExample example = new RoleExample();
    Criteria criteria = example.createCriteria();

    criteria.andNameEqualTo(name);
    int count = roleMapper.countByExample(example);

    return count > 0 ? true : false;
  }

  @Override
  public boolean add(RoleAdd roleAdd) {
    Role role = new Role();
    BeanUtils.copyProperties(roleAdd, role);
    String id = UUID.randomUUID().toString();
    role.setId(id);
    role.setCreatorId(tokenManager.getUserId());

    return roleMapper.insertSelective(role) == 1 ? true : false;
  }

  @Override
  public boolean update(RoleUpdate roleUpdate) {
    Role role = new Role();
    BeanUtils.copyProperties(roleUpdate, role);
    role.setModifierId(tokenManager.getUserId());

    return roleMapper.updateByPrimaryKeySelective(role) == 1 ? true : false;
  }

  @Override
  public RoleSearchResp select(String id) {
    Role role = roleMapper.selectByPrimaryKey(id);
    RoleSearchResp roleSearchResp = new RoleSearchResp();
    BeanUtils.copyProperties(role, roleSearchResp);

    return roleSearchResp;
  }

  @Override
  @Transactional(rollbackFor = {RuntimeException.class, Error.class})
  public boolean delete(String id) {
    //从角色用户表，删除角色
    roleUserService.deleteRole(id);

    //从角色菜单表，删除角色
    roleMenuService.deleteRole(id);

    //删除角色
    roleMapper.deleteByPrimaryKey(id);

    return true;
  }

  @Override
  public boolean addUser(RoleUserAdd roleUserAdd) {
    return roleUserService.addUser(roleUserAdd);
  }

  @Override
  public boolean addMenu(RoleMenuAdd roleMenuAdd) {
    //先删除旧的菜单分配
    RoleMenuDel roleMenuDel = new RoleMenuDel();
    BeanUtils.copyProperties(roleMenuAdd, roleMenuDel);
    deleteMenu(roleMenuDel);

    return roleMenuService.addMenu(roleMenuAdd);
  }

  @Override
  public boolean deleteUser(RoleUserDel roleUserDel) {
    return roleUserService.deleteUser(roleUserDel);
  }

  @Override
  public boolean deleteMenu(RoleMenuDel roleMenuDel) {
    return roleMenuService.deleteMenu(roleMenuDel);
  }
}
