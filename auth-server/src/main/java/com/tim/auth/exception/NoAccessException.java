package com.tim.auth.exception;

import com.tim.exception.type.CommonException;

/**
 * @author：tim
 * @date：20-6-5 下午11:13
 * @description： 无权限异常
 */
public class NoAccessException extends CommonException {

  public NoAccessException(String msg) {
    super(msg);
  }
}
