package com.tim.auth.service.impl;

import com.tim.auth.component.TokenManager;
import com.tim.auth.dao.RoleUserMapper;
import com.tim.auth.po.RoleUser;
import com.tim.auth.po.RoleUserExample;
import com.tim.auth.service.RoleUserService;
import com.tim.auth.vo.RoleUserAdd;
import com.tim.auth.vo.RoleUserDel;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author：tim
 * @date：20-3-1 下午5:56
 * @description：
 */
@Service
public class RoleUserServiceImpl implements RoleUserService {

  @Autowired
  private RoleUserMapper roleUserMapper;

  @Autowired
  private TokenManager tokenManager;

  @Override
  public Boolean addUser(RoleUserAdd roleUserAdd) {
    String roleId = roleUserAdd.getRoleId();
    List<String> userIdList = roleUserAdd.getUserIdList();

    for (String userId : userIdList) {
      RoleUser roleUser = new RoleUser();
      roleUser.setId(UUID.randomUUID().toString());
      roleUser.setRoleid(roleId);
      roleUser.setUserid(userId);
      roleUser.setCreatorId(tokenManager.getUserId());

      roleUserMapper.insertSelective(roleUser);
    }

    return true;
  }

  @Override
  public Boolean deleteUser(RoleUserDel roleUserDel) {
    String roleId = roleUserDel.getRoleId();
    List<String> userIdList = roleUserDel.getUserIdList();

    RoleUserExample roleUserExample = new RoleUserExample();
    RoleUserExample.Criteria criteria = roleUserExample.createCriteria();
    criteria.andRoleidEqualTo(roleId);
    criteria.andUseridIn(userIdList);

    return roleUserMapper.deleteByExample(roleUserExample) > 0 ? true : false;
  }

  @Override
  public Boolean deleteUser(String userId) {
    RoleUserExample roleUserExample = new RoleUserExample();
    RoleUserExample.Criteria criteria = roleUserExample.createCriteria();
    criteria.andUseridEqualTo(userId);
    roleUserMapper.deleteByExample(roleUserExample);

    return true;
  }

  @Override
  public Boolean deleteRole(String roleId) {
    RoleUserExample roleUserExample = new RoleUserExample();
    RoleUserExample.Criteria criteria = roleUserExample.createCriteria();
    criteria.andRoleidEqualTo(roleId);
    roleUserMapper.deleteByExample(roleUserExample);

    return true;
  }
}
