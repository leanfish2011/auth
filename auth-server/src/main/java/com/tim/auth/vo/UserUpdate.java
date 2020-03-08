package com.tim.auth.vo;

import lombok.Data;

@Data
public class UserUpdate {

  private String id;
  private String usercode;
  private String password;
  private String name;
  private String email;
}
