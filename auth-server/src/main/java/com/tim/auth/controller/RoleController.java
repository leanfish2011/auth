package com.tim.auth.controller;

import com.tim.auth.sdk.constant.AuthConstant;
import com.tim.auth.service.MenuService;
import com.tim.auth.service.RoleService;
import com.tim.auth.service.UserService;
import com.tim.auth.vo.RoleAdd;
import com.tim.auth.vo.RoleMenuAdd;
import com.tim.auth.vo.RoleSearchReq;
import com.tim.auth.vo.RoleSearchResp;
import com.tim.auth.vo.RoleUpdate;
import com.tim.auth.vo.RoleUserAdd;
import com.tim.auth.vo.RoleUserDel;
import com.tim.auth.vo.UserSearchResp;
import com.tim.message.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @Autowired
  private MenuService menuService;

  @Autowired
  private UserService userService;

  @ApiOperation(value = "查询角色")
  @RequestMapping(method = RequestMethod.GET)
  public Message search(RoleSearchReq roleSearchReq) {
    List<RoleSearchResp> rolesList = roleService.search(roleSearchReq);

    return Message.success(rolesList);
  }

  @ApiOperation(value = "删除角色")
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public Message delete(@PathVariable String id) {
    if (id.equals(AuthConstant.ROLE_ADMIN_ID) || id.equals(AuthConstant.ROLE_COMMON_ID)) {
      return Message.error("系统内置角色不能删除！");
    }

    boolean result = roleService.delete(id);
    if (!result) {
      return Message.error();
    }

    return Message.success();
  }

  @ApiOperation(value = "新增角色")
  @RequestMapping(method = RequestMethod.POST)
  public Message add(@RequestBody RoleAdd roleAdd) {
    boolean result = roleService.add(roleAdd);
    if (!result) {
      return Message.error();
    }

    return Message.success();
  }

  @ApiOperation(value = "修改角色")
  @RequestMapping(method = RequestMethod.PUT)
  public Message update(@RequestBody RoleUpdate roleUpdate) {
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
  public Message addUser(@RequestBody RoleUserAdd roleUserAdd) {
    boolean result = roleService.addUser(roleUserAdd);
    if (!result) {
      return Message.error();
    }

    return Message.success();
  }

  @ApiOperation(value = "角色删除用户")
  @DeleteMapping("/user")
  public Message deleteUser(@RequestBody RoleUserDel roleUserDel) {
    boolean result = roleService.deleteUser(roleUserDel);
    if (!result) {
      return Message.error();
    }

    return Message.success();
  }

  @ApiOperation(value = "根据角色id列出菜单")
  @GetMapping("/menu/{roleId}")
  public Message<List<String>> listMenuRole(@PathVariable String roleId) {
    List<String> menuList = menuService.listMenuRole(roleId);

    return Message.success(menuList);
  }

  @ApiOperation(value = "角色增加菜单")
  @PostMapping("/menu")
  public Message addMenu(@RequestBody RoleMenuAdd roleMenuAdd) {
    String roleId = roleMenuAdd.getRoleId();
    if (roleId.equals(AuthConstant.ROLE_ADMIN_ID) || roleId.equals(AuthConstant.ROLE_COMMON_ID)) {
      return Message.error("系统内置角色权限不能修改！");
    }

    boolean result = roleService.addMenu(roleMenuAdd);
    if (!result) {
      return Message.error();
    }

    return Message.success();
  }

  @ApiOperation(value = "获取角色下用户")
  @RequestMapping(value = "/user/{roleId}", method = RequestMethod.GET)
  public Message<List<UserSearchResp>> roleUser(@PathVariable String roleId) {
    return Message.success(userService.roleUser(roleId));
  }

}
