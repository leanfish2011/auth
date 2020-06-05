package com.tim.auth.exception;

import com.tim.exception.type.CommonException;

/**
 * @author：tim
 * @date：20-6-5 下午10:23
 * @description： 不能修改异常
 */
public class NotModifyException extends CommonException {

  public NotModifyException(String msg) {
    super(msg);
  }
}
