package com.tim.auth.dao;

import com.tim.auth.po.Menu;
import com.tim.auth.po.MenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuMapper {

  int countByExample(MenuExample example);

  int deleteByExample(MenuExample example);

  int deleteByPrimaryKey(String id);

  int insert(Menu record);

  int insertSelective(Menu record);

  List<Menu> selectByExample(MenuExample example);

  Menu selectByPrimaryKey(String id);

  int updateByExampleSelective(@Param("record") Menu record,
      @Param("example") MenuExample example);

  int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

  int updateByPrimaryKeySelective(Menu record);

  int updateByPrimaryKey(Menu record);

  List<Menu> selectByUserId(String userId);

  List<String> selectByRoleId(String roleId);

}
