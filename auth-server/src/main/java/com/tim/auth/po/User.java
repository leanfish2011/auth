package com.tim.auth.po;

import java.util.Date;
import lombok.Data;

@Data
public class User {

  private String id;

  private Date createTime;

  private String creatorId;

  private Date modifiedTime;

  private String modifierId;

  private String usercode;

  private String password;

  private String name;

  private String email;

  private String photourl;

  private String photoFingerprint;
}