package com.tim.auth.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * @author：tim
 * @date：20-3-1 下午6:49
 * @description：
 */
@Data
public class LoginResp implements Serializable {

  /**
   * 用户token
   */
  private String token;

  /**
   * 用户id
   */
  private String userId;

  /**
   * 用户编码
   */
  private String userCode;

  /**
   * 用户名称
   */
  private String name;
}
