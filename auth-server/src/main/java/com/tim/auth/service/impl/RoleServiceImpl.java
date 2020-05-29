package com.tim.auth.service.impl;

import com.tim.auth.component.LoadResourceRole;
import com.tim.auth.component.TokenManager;
import com.tim.auth.dao.RoleMapper;
import com.tim.auth.po.Role;
import com.tim.auth.po.RoleExample;
import com.tim.auth.po.RoleExample.Criteria;
import com.tim.auth.sdk.constant.AuthConstant;
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
import com.tim.message.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleMapper roleMapper;

  @Autowired
  private RoleMenuService roleMenuService;

  @Autowired
  private RoleUserService roleUserService;

  @Autowired
  private TokenManager tokenManager;

  @Autowired
  private LoadResourceRole loadResourceRole;

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
  public Message add(RoleAdd roleAdd) {
    Role role = new Role();
    BeanUtils.copyProperties(roleAdd, role);
    String id = UUID.randomUUID().toString();
    role.setId(id);
    role.setCreatorId(tokenManager.getUserId());

    int row = roleMapper.insertSelective(role);
    if (row == 1) {
      return Message.success("新增角色成功！");
    }

    return Message.error("新增角色失败！");
  }

  @Override
  public Message update(RoleUpdate roleUpdate) {
    Role role = new Role();
    BeanUtils.copyProperties(roleUpdate, role);
    role.setModifierId(tokenManager.getUserId());

    int row = roleMapper.updateByPrimaryKeySelective(role);
    if (row == 1) {
      return Message.success("更新角色成功！");
    }

    return Message.error("更新角色失败！");
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
  public Message delete(String id) {
    if (id.equals(AuthConstant.ROLE_ADMIN_ID) || id.equals(AuthConstant.ROLE_COMMON_ID)) {
      log.warn("系统内置角色不能删除，id:{}", id);
      return Message.error("系统内置角色不能删除！");
    }

    //从角色用户表，删除角色
    roleUserService.deleteRole(id);

    //从角色菜单表，删除角色
    roleMenuService.deleteRole(id);

    //删除角色
    roleMapper.deleteByPrimaryKey(id);

    //刷新redis
    loadResourceRole.load();

    return Message.success("角色删除成功！");
  }

  @Override
  public Message addUser(RoleUserAdd roleUserAdd) {
    //先删除旧的用户
    roleUserService.deleteRole(roleUserAdd.getRoleId());

    //刷新redis
    loadResourceRole.load();
    roleUserService.addUser(roleUserAdd);

    return Message.success();
  }

  @Override
  public Message addMenu(RoleMenuAdd roleMenuAdd) {
    String roleId = roleMenuAdd.getRoleId();
    if (roleId.equals(AuthConstant.ROLE_ADMIN_ID) || roleId.equals(AuthConstant.ROLE_COMMON_ID)) {
      log.warn("系统内置角色权限不能修改，id:{}", roleId);
      return Message.error("系统内置角色权限不能修改！");
    }

    //先删除旧的菜单分配
    roleMenuService.deleteRole(roleMenuAdd.getRoleId());

    //刷新redis
    loadResourceRole.load();

    roleMenuService.addMenu(roleMenuAdd);

    return Message.success();
  }

  @Override
  public Message deleteUser(RoleUserDel roleUserDel) {
    boolean result = roleUserService.deleteUser(roleUserDel);
    if (!result) {
      return Message.error();
    }

    return Message.success();
  }

  @Override
  public boolean deleteMenu(RoleMenuDel roleMenuDel) {
    return roleMenuService.deleteMenu(roleMenuDel);
  }
}
