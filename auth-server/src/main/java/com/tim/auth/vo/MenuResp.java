package com.tim.auth.vo;

import java.util.Date;
import lombok.Data;

/**
 * @author：tim
 * @date：20-3-1 上午11:22
 * @description：
 */
@Data
public class MenuResp {

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
}
