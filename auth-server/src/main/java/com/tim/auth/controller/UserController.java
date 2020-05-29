package com.tim.auth.controller;

import com.tim.auth.sdk.constant.AuthConstant;
import com.tim.auth.service.UserService;
import com.tim.auth.vo.UserAdd;
import com.tim.auth.vo.UserSearchReq;
import com.tim.auth.vo.UserSearchResp;
import com.tim.auth.vo.UserUpdate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tim.message.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "用户管理")
@RestController
@RequestMapping("${api.version.module}/user")
public class UserController {

  @Autowired
  private UserService userService;

  @ApiOperation(value = "查询用户")
  @RequestMapping(method = RequestMethod.GET)
  public Message<List<UserSearchResp>> search(UserSearchReq userSearchReq) {
    return Message.success(userService.search(userSearchReq));
  }

  @ApiOperation(value = "获取用户信息")
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Message<UserSearchResp> select(@PathVariable String id) {
    return Message.success(userService.select(id));
  }

  @ApiOperation(value = "新增用户")
  @RequestMapping(method = RequestMethod.POST)
  public Message add(@RequestBody UserAdd userAdd) {
    boolean isExist = userService.isExist(userAdd.getUserCode());
    if (isExist) {
      return Message.error("该用户名已经存在！");
    }

    boolean result = userService.add(userAdd);
    if (!result) {
      return Message.error("新增用户失败！");
    }

    return Message.success("新增用户成功！");
  }

  @ApiOperation(value = "修改用户")
  @RequestMapping(method = RequestMethod.PUT)
  public Message update(@RequestBody UserUpdate userUpdate) {
    return userService.update(userUpdate);
  }

  @ApiOperation(value = "删除用户")
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public Message delete(@PathVariable String id) {
    if (id.equals(AuthConstant.USER_ADMIN_ID) || id.equals(AuthConstant.USER_COMMON_ID)) {
      return Message.error("系统内置用户不能删除！");
    }

    boolean result = userService.delete(id);
    if (!result) {
      return Message.error();
    }

    return Message.success();
  }

}
