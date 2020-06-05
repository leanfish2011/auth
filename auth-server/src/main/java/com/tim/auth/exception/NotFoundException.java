package com.tim.auth.exception;

import com.tim.exception.type.CommonException;

/**
 * @author：tim
 * @date：20-6-5 下午10:51
 * @description： 未找到异常
 */
public class NotFoundException extends CommonException {

  public NotFoundException(String msg) {
    super(msg);
  }
}
