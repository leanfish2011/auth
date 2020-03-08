package com.tim.auth.exception;

import com.tim.exception.CommonException;

/**
 * @author：tim
 * @date：20-3-1 下午9:37
 * @description：
 */
public class NullTokenException extends CommonException {

  public NullTokenException(String msg) {
    super(msg);
  }
}
