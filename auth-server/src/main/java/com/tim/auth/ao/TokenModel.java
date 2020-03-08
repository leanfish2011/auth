package com.tim.auth.ao;

import com.tim.auth.vo.LoginResp;
import java.io.Serializable;
import lombok.Data;

/**
 * token数据格式
 *
 * @author tim
 * @time 2018-05-27 19:25:52
 */
@Data
public class TokenModel implements Serializable {

  /**
   * 用户token
   */
  private String token;

  /**
   * 用户信息
   */
  private LoginResp loginResp;
}
