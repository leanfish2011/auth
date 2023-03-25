package com.tim.auth.service.impl;

import com.tim.auth.component.LoadResourceRole;
import com.tim.auth.component.TokenManager;
import com.tim.auth.dao.RoleMapper;
import com.tim.auth.exception.NotModifyException;
import com.tim.auth.po.Role;
import com.tim.auth.po.RoleExample;
import com.tim.auth.po.RoleExample.Criteria;
import com.tim.auth.sdk.constant.AuthConstant;
import com.tim.auth.service.RoleMenuService;
import com.tim.auth.service.RoleService;
import com.tim.auth.service.RoleUserService;
import com.tim.auth.sdk.vo.RoleAdd;
import com.tim.auth.sdk.vo.RoleMenuAdd;
import com.tim.auth.sdk.vo.RoleSearchReq;
import com.tim.auth.sdk.vo.RoleSearchResp;
import com.tim.auth.sdk.vo.RoleUpdate;
import com.tim.auth.sdk.vo.RoleUserAdd;
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
  public Boolean isExist(String name) {
    RoleExample example = new RoleExample();
    Criteria criteria = example.createCriteria();

    criteria.andNameEqualTo(name);
    int count = roleMapper.countByExample(example);

    return count > 0 ? true : false;
  }

  @Override
  public Boolean add(RoleAdd roleAdd) {
    Role role = new Role();
    BeanUtils.copyProperties(roleAdd, role);
    String id = UUID.randomUUID().toString();
    role.setId(id);
    role.setCreatorId(tokenManager.getUserId());

    roleMapper.insertSelective(role);

    log.info("新增角色成功！");
    return true;
  }

  @Override
  public Boolean update(RoleUpdate roleUpdate) {
    Role role = new Role();
    BeanUtils.copyProperties(roleUpdate, role);
    role.setModifierId(tokenManager.getUserId());

    roleMapper.updateByPrimaryKeySelective(role);
    log.info("更新角色成功！");
    return true;
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
  public Boolean delete(String id) {
    if (id.equals(AuthConstant.ROLE_ADMIN_ID) || id.equals(AuthConstant.ROLE_COMMON_ID)) {
      log.warn("系统内置角色不能删除，id:{}", id);
      throw new NotModifyException("系统内置角色不能删除！");
    }

    //从角色用户表，删除角色
    roleUserService.deleteRole(id);

    //从角色菜单表，删除角色
    roleMenuService.deleteRole(id);

    //删除角色
    roleMapper.deleteByPrimaryKey(id);

    //刷新redis
    loadResourceRole.load();

    log.info("角色删除成功！");
    return true;
  }

  @Override
  public Boolean addUser(RoleUserAdd roleUserAdd) {
    //先删除旧的用户
    roleUserService.deleteRole(roleUserAdd.getRoleId());

    roleUserService.addUser(roleUserAdd);

    //刷新redis
    loadResourceRole.load();

    log.info("角色分配用户成功！");

    return true;
  }

  @Override
  public Boolean addMenu(RoleMenuAdd roleMenuAdd) {
    String roleId = roleMenuAdd.getRoleId();
    if (roleId.equals(AuthConstant.ROLE_ADMIN_ID) || roleId.equals(AuthConstant.ROLE_COMMON_ID)) {
      log.warn("系统内置角色权限不能修改，id:{}", roleId);
      throw new NotModifyException("系统内置角色权限不能修改！");
    }

    //先删除旧的菜单分配
    roleMenuService.deleteRole(roleMenuAdd.getRoleId());

    //增加新的菜单
    roleMenuService.addMenu(roleMenuAdd);

    //刷新redis
    loadResourceRole.load();

    log.info("角色分配权限成功！");

    return true;
  }

}
