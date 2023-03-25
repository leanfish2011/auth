package com.tim.auth.sdk.vo;

import lombok.Data;

/**
 * @author：tim
 * @date：20-3-1 下午6:38
 * @description：
 */
@Data
public class LoginReq {

  private String userCode;
  private String password;
}
