package com.tim.auth.interceptor;

import com.tim.auth.util.ResponseUtil;
import com.tim.auth.component.RequestManager;
import com.tim.auth.component.ResourceManager;
import com.tim.message.MainCode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.tim.message.Message;

/**
 * 资源请求拦截器，检查用户是否有权限请求该资源
 *
 * @author tim
 * @time 2018-05-12 17:00:58
 */
@Component
public class ResourceInterceptor implements HandlerInterceptor {

  @Autowired
  private ResourceManager resourceManager;

  @Autowired
  private RequestManager requestManager;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    // TODO，请求的资源是完整路径，数据库是前缀，如何转换？
    // TODO，请求的资源路径待和前端一起确定
    // TODO，请求的资源需要过滤，调试再看
    String getRequestURI = request.getRequestURI();// 请求的资源
    String token = requestManager.getAccessToken();

    Message message = resourceManager.checkPermission(getRequestURI, token);

    if (message.getCode() == MainCode.SUCCESS) {
      return true;
    }

    ResponseUtil.responseOutWithJson(response, message);
    return false;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) throws Exception {
  }

  @Override
  public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
      ModelAndView arg3) throws Exception {
  }

}
