package com.tim.auth.controller;

import com.tim.auth.service.UserService;
import com.tim.auth.sdk.vo.UserAdd;
import com.tim.auth.sdk.vo.UserSearchReq;
import com.tim.auth.sdk.vo.UserSearchResp;
import com.tim.auth.sdk.vo.UserSearchRespData;
import com.tim.auth.sdk.vo.UserUpdate;

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
  public Message<UserSearchRespData> search(UserSearchReq userSearchReq) {
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
    userService.add(userAdd);
    return Message.success();
  }

  @ApiOperation(value = "修改用户")
  @RequestMapping(method = RequestMethod.PUT)
  public Message update(@RequestBody UserUpdate userUpdate) {
    userService.update(userUpdate);
    return Message.success();
  }

  @ApiOperation(value = "删除用户")
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public Message delete(@PathVariable String id) {
    userService.delete(id);
    return Message.success();
  }

}
