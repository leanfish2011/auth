package com.tim.auth.config;

import com.tim.auth.common.AuthCode;
import com.tim.auth.exception.InvalidTokenException;
import com.tim.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author：tim
 * @date：20-6-5 下午11:06
 * @description： auth模块特殊的异常处理
 */
@ControllerAdvice
@Slf4j
public class AuthExceptionHandler {

  /**
   * 拦截捕捉自定义异常 InvalidTokenException.class
   */
  @ResponseBody
  @ExceptionHandler(InvalidTokenException.class)
  public Message myErrorHandler(InvalidTokenException ex) {
    return new Message(AuthCode.INVALIDTOKEN, AuthCode.INVALIDTOKEN_MSG);

  }
}
