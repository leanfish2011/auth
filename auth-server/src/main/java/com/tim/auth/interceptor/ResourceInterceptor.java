package com.tim.auth.interceptor;

import com.tim.auth.component.RequestManager;
import com.tim.auth.service.AccessService;
import com.tim.util.ResponseUtil;
import com.tim.message.MainCode;
import com.tim.message.Message;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 资源请求拦截器，检查用户是否有权限请求该资源
 *
 * @author tim
 * @time 2018-05-12 17:00:58
 */
@Component
public class ResourceInterceptor implements HandlerInterceptor {

  @Autowired
  private RequestManager requestManager;

  @Autowired
  private AccessService accessService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    boolean hasPermission = accessService
        .checkPermission(requestManager.getRequestURI(), requestManager.getRequestMethod());
    if (hasPermission) {
      return true;
    }

    // TODO
    //ResponseUtil.responseOutWithJson(response, message);
    return false;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {
  }

  @Override
  public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
      ModelAndView arg3) {
  }

}
