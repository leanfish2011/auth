package com.tim.auth.vo;

import lombok.Data;

/**
 * @author：tim
 * @date：20-3-21 下午8:54
 * @description：
 */
@Data
public class RegisterReq {

  private String userCode;
  private String password;
  private String name;
  private String email;
}
