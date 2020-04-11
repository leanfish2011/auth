package com.tim.auth.controller;

import com.tim.auth.sdk.vo.TokenModel;
import com.tim.auth.service.AccessService;
import com.tim.auth.vo.LoginReq;
import com.tim.auth.sdk.vo.LoginResp;
import com.tim.auth.vo.RegisterReq;
import com.tim.auth.vo.UpdatePwdReq;
import com.tim.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 作为认证服务，提供功能： 1、生成token； 2、验证token，即通过token查找用户； token管理自己实现。
 *
 * @author tim
 * @time 2018-05-27 19:30:26
 */
@Api(description = "账号管理")
@RestController
@RequestMapping("${api.version.module}/access")
public class AccessController {

  @Autowired
  private AccessService accessService;

  @ApiOperation(value = "登录")
  @PostMapping("/login")
  public Message<LoginResp> login(@RequestBody LoginReq loginReq) {
    return accessService.login(loginReq);
  }

  @ApiOperation(value = "退出", notes = "需要登录,token放入Header中")
  @GetMapping("/logout")
  public Message logout() {
    return accessService.logout();
  }

  @ApiOperation(value = "注册")
  @PostMapping("/register")
  public Message register(@RequestBody RegisterReq registerReq) {
    return accessService.register(registerReq);
  }

  @ApiOperation(value = "通过token查自己信息", notes = "需要登录,token放入Header中")
  @GetMapping("/profile")
  public Message<TokenModel> profile() {
    return accessService.profile();
  }

  @ApiOperation(value = "检查token是否有效", notes = "需要登录,token放入Header中")
  @GetMapping("/check")
  public Message check() {
    return accessService.check();
  }

  @ApiOperation(value = "检查是否有权限", notes = "需要登录,token放入Header中")
  @RequestMapping(value = "/permission", method = RequestMethod.GET)
  public Message checkPermission(@RequestParam(value = "uri") String uri,
      @RequestParam(value = "method") String method) {
    return accessService.checkPermission(uri, method);
  }

  @ApiOperation(value = "修改密码", notes = "需要登录,token放入Header中")
  @PutMapping("/password")
  public Message updatePassword(@RequestBody @Validated UpdatePwdReq updatePwdReq) {
    return accessService.updatePassword(updatePwdReq);
  }

}
