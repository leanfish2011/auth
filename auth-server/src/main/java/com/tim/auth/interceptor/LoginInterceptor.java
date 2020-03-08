package com.tim.auth.interceptor;

import com.tim.auth.util.ResponseUtil;
import com.tim.auth.component.RequestManager;
import com.tim.auth.component.TokenManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.tim.message.Message;

/**
 * 登录拦截器，检查用户是否登录
 *
 * @author tim
 * @time 2018-05-12 17:00:58
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

  @Autowired
  private RequestManager requestManager;

  @Autowired
  private TokenManager tokenManager;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    Message message;

    //1、检查token是否传入
    String token = requestManager.getAccessToken();
    if (StringUtils.isEmpty(token)) {
      message = Message.error("未找到token");
      ResponseUtil.responseOutWithJson(response, message);
      return false;
    }

    //2、检查token是否有效
    boolean isValid = tokenManager.checkToken(token);
    if (!isValid) {
      message = Message.error("token无效！");
      ResponseUtil.responseOutWithJson(response, message);
      return false;
    }

    return true;
  }

  @Override
  public void postHandle(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {
  }

  @Override
  public void afterCompletion(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, Exception e) {
  }

}
