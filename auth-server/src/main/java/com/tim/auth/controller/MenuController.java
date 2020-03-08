package com.tim.auth.controller;

import com.tim.auth.service.MenuService;
import com.tim.auth.vo.MenuTree;
import com.tim.message.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：tim
 * @date：20-3-1 上午11:18
 * @description：
 */
@Api(description = "菜单管理")
@RestController
@RequestMapping("${api.version.module}/menu")
public class MenuController {

  @Autowired
  private MenuService menuService;

  @ApiOperation(value = "列出菜单")
  @RequestMapping(method = RequestMethod.GET)
  public Message<List<MenuTree>> listTree() {
    List<MenuTree> menuTreeList = menuService.listTree();

    return Message.success(menuTreeList);
  }

  @ApiOperation(value = "根据用户id列出菜单")
  @GetMapping("/user")
  public Message<List<MenuTree>> listTreeUser(String userId) {
    List<MenuTree> menuTreeList = menuService.listTreeUser(userId);

    return Message.success(menuTreeList);
  }

  @ApiOperation(value = "根据角色id列出菜单")
  @GetMapping("/role")
  public Message<List<MenuTree>> listTreeRole(String roleId) {
    List<MenuTree> menuTreeList = menuService.listTreeRole(roleId);

    return Message.success(menuTreeList);
  }
}
