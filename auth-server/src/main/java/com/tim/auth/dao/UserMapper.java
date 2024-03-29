package com.tim.auth.dao;

import com.tim.auth.ao.ResourceRole;
import com.tim.auth.ao.UserRole;
import com.tim.auth.po.User;
import com.tim.auth.po.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

  int countByExample(UserExample example);

  int deleteByExample(UserExample example);

  int deleteByPrimaryKey(String id);

  int insert(User record);

  int insertSelective(User record);

  List<User> selectByExample(UserExample example);

  User selectByPrimaryKey(String id);

  int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

  int updateByExample(@Param("record") User record, @Param("example") UserExample example);

  int updateByPrimaryKeySelective(User record);

  int updateByPrimaryKey(User record);

  /**
   * 加载权限角色
   */
  List<ResourceRole> loadRequestResouce();

  List<User> selectByRoleId(String roleId);

  UserRole selectUserRole(String userId);

}
