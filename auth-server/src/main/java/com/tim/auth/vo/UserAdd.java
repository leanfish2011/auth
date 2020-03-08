package com.tim.auth.vo;

import lombok.Data;

/**
 * 新增用户参数
 * 
 * @author tim
 * @time 2018-05-13 16:49:40
 */
@Data
public class UserAdd {

  private String userCode;
  private String password;
  private String name;
  private String email;

}
