package com.tim.auth.controller;

import com.tim.auth.service.MenuService;
import com.tim.auth.service.RoleService;
import com.tim.auth.service.UserService;
import com.tim.auth.sdk.vo.RoleAdd;
import com.tim.auth.sdk.vo.RoleMenuAdd;
import com.tim.auth.sdk.vo.RoleSearchReq;
import com.tim.auth.sdk.vo.RoleUpdate;
import com.tim.auth.sdk.vo.RoleUserAdd;
import com.tim.auth.sdk.vo.UserSearchResp;
import com.tim.message.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    return Message.success(roleService.search(roleSearchReq));
  }

  @ApiOperation(value = "删除角色")
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public Message delete(@PathVariable String id) {
    roleService.delete(id);
    return Message.success();
  }

  @ApiOperation(value = "新增角色")
  @RequestMapping(method = RequestMethod.POST)
  public Message add(@RequestBody RoleAdd roleAdd) {
    roleService.add(roleAdd);
    return Message.success();
  }

  @ApiOperation(value = "修改角色")
  @RequestMapping(method = RequestMethod.PUT)
  public Message update(@RequestBody RoleUpdate roleUpdate) {
    roleService.update(roleUpdate);
    return Message.success();
  }

  @ApiOperation(value = "获取角色信息")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Message select(@PathVariable String id) {
    return Message.success(roleService.select(id));
  }

  @ApiOperation(value = "角色增加用户")
  @PostMapping("/user")
  public Message addUser(@RequestBody RoleUserAdd roleUserAdd) {
    roleService.addUser(roleUserAdd);
    return Message.success();
  }

  @ApiOperation(value = "根据角色id列出菜单(只列出叶子节点)")
  @GetMapping("/menu/{roleId}")
  public Message<List<String>> listMenuRole(@PathVariable String roleId) {
    return Message.success(menuService.listMenuRole(roleId));
  }

  @ApiOperation(value = "角色增加菜单")
  @PostMapping("/menu")
  public Message addMenu(@RequestBody RoleMenuAdd roleMenuAdd) {
    roleService.addMenu(roleMenuAdd);
    return Message.success();
  }

  @ApiOperation(value = "获取角色下用户")
  @RequestMapping(value = "/user/{roleId}", method = RequestMethod.GET)
  public Message<List<UserSearchResp>> roleUser(@PathVariable String roleId) {
    return Message.success(userService.roleUser(roleId));
  }

}
