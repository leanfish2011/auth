package com.tim.auth.controller;

import com.tim.auth.service.RoleService;
import com.tim.auth.vo.RoleAdd;
import com.tim.auth.vo.RoleMenuAdd;
import com.tim.auth.vo.RoleMenuDel;
import com.tim.auth.vo.RoleSearchReq;
import com.tim.auth.vo.RoleSearchResp;
import com.tim.auth.vo.RoleUpdate;
import com.tim.auth.vo.RoleUserAdd;
import com.tim.auth.vo.RoleUserDel;
import com.tim.message.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：Tim
 * @date：2017年9月16日 上午9:45:27
 * @description：角色控制器
 */
@Api(description = "角色管理")
@RestController
@RequestMapping("${api.version.module}/role")
public class RoleController {

  @Autowired
  private RoleService roleService;

  @ApiOperation(value = "查询角色")
  @RequestMapping(method = RequestMethod.GET)
  public Message search(RoleSearchReq roleSearchReq) {
    List<RoleSearchResp> rolesList = roleService.search(roleSearchReq);

    return Message.success(rolesList);
  }

  @ApiOperation(value = "删除角色")
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public Message delete(@PathVariable String id) {
    boolean result = roleService.delete(id);
    if (!result) {
      return Message.error();
    }

    return Message.success();
  }

  @ApiOperation(value = "新增角色")
  @RequestMapping(method = RequestMethod.POST)
  public Message add(RoleAdd roleAdd) {
    boolean result = roleService.add(roleAdd);
    if (!result) {
      return Message.error();
    }

    return Message.success();
  }

  @ApiOperation(value = "修改角色")
  @RequestMapping(method = RequestMethod.PUT)
  public Message update(RoleUpdate roleUpdate) {
    boolean result = roleService.update(roleUpdate);
    if (!result) {
      return Message.error();
    }

    return Message.success();
  }

  @ApiOperation(value = "获取角色信息")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Message select(@PathVariable String id) {
    RoleSearchResp roleSearchResp = roleService.select(id);

    return Message.success(roleSearchResp);
  }

  @ApiOperation(value = "角色增加用户")
  @PostMapping("/user")
  public Message addUser(RoleUserAdd roleUserAdd) {
    boolean result = roleService.addUser(roleUserAdd);
    if (!result) {
      return Message.error();
    }

    return Message.success();
  }

  @ApiOperation(value = "角色增加菜单")
  @PostMapping("/menu")
  public Message addMenu(RoleMenuAdd roleMenuAdd) {
    boolean result = roleService.addMenu(roleMenuAdd);
    if (!result) {
      return Message.error();
    }

    return Message.success();
  }

  @ApiOperation(value = "角色删除用户")
  @DeleteMapping("/user")
  public Message deleteUser(RoleUserDel roleUserDel) {
    boolean result = roleService.deleteUser(roleUserDel);
    if (!result) {
      return Message.error();
    }

    return Message.success();
  }

  @ApiOperation(value = "角色删除菜单")
  @DeleteMapping("/menu")
  public Message deleteMenu(RoleMenuDel roleMenuDel) {
    boolean result = roleService.deleteMenu(roleMenuDel);
    if (!result) {
      return Message.error();
    }

    return Message.success();
  }
}
