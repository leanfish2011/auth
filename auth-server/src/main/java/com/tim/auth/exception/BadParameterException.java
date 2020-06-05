package com.tim.auth.exception;

import com.tim.exception.type.CommonException;

/**
 * @author：tim
 * @date：20-6-5 下午10:55
 * @description： 输入参数不对异常
 */
public class BadParameterException extends CommonException {

  public BadParameterException(String msg) {
    super(msg);
  }
}
