package com.tim.auth.component;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author：tim
 * @date：20-3-1 下午9:31
 * @description：
 */
@Component
public class RequestManager {

  @Autowired
  protected HttpServletRequest request;

  /**
   * 获取当前登录用户token
   */
  public String getAccessToken() {
    String token =
        request.getHeader("authorization") == null ? request.getHeader("Authorization")
            : request.getHeader("authorization");

    if (StringUtils.isEmpty(token)) {
      return null;
    }

    return token.trim();
  }
}
