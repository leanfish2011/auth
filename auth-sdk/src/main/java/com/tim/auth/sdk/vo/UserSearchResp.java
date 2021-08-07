package com.tim.auth.sdk.vo;

import java.util.Date;
import lombok.Data;

/**
 * @author：Tim
 * @date：2018年1月21日 下午10:35:58
 * @description：搜索用户返回结果
 */
@Data
public class UserSearchResp {

  private String id;
  private String userCode;
  private String name;
  private String email;
  private Date createTime;
  private Date modifiedTime;
  private String password;
  private String photourl;
}
