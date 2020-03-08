package com.tim.auth.po;

import java.util.Date;
import lombok.Data;

@Data
public class Role {

  private String id;

  private Date createTime;

  private String creatorId;

  private Date modifiedTime;

  private String modifierId;

  private String name;

  private String remark;
}