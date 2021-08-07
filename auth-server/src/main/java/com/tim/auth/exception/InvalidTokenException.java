package com.tim.auth.exception;


import com.tim.exception.type.CommonException;

/**
 * @author：tim
 * @date：20-3-1 下午9:37
 * @description： 无效token异常
 */
public class InvalidTokenException extends CommonException {

  public InvalidTokenException(String msg) {
    super(msg);
  }
}
