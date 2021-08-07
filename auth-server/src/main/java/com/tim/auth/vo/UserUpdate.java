package com.tim.auth.vo;

import lombok.Data;

@Data
public class UserUpdate {

  private String id;
  private String userCode;
  private String password;
  private String name;
  private String email;
  private String photourl;
}
