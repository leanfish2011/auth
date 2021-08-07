package com.tim.auth.exception;

import com.tim.exception.type.CommonException;

/**
 * @author：tim
 * @date：20-6-5 下午9:43
 * @description： 重复异常
 */
public class DuplicateException extends CommonException {

  public DuplicateException(String msg) {
    super(msg);
  }
}
