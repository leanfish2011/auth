package com.tim.auth.po;

import java.util.Date;
import lombok.Data;

@Data
public class Menu {

  private String id;

  private Date createTime;

  private String creatorId;

  private Date modifiedTime;

  private String modifierId;

  private String name;

  private String url;

  private String requestPath;

  private String parentId;

  private Integer sortNum;

  private Integer isShow;
}
