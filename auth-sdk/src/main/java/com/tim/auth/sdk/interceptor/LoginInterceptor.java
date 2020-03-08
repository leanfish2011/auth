package com.tim.auth.sdk.interceptor;

import com.tim.auth.sdk.feign.AccountFeignClient;
import com.tim.auth.sdk.util.ResponseUtil;
import com.tim.message.MainCode;
import com.tim.message.Message;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录拦截器，检查用户是否登录
 *
 * @author tim
 * @time 2018-05-12 17:00:58
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

  @Autowired
  private AccountFeignClient accountFeignClient;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    //检查token是否有效
    Message message = accountFeignClient.check();
    if (message.getCode() == MainCode.SUCCESS) {
      return true;
    }

    ResponseUtil.responseOutWithJson(response, message);
    return false;
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
