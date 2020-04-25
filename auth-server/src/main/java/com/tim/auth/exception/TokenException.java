package com.tim.auth.exception;


import com.tim.exception.type.CommonException;

/**
 * @author：tim
 * @date：20-3-1 下午9:37
 * @description：
 */
public class TokenException extends CommonException {

  public TokenException(String msg) {
    super("Token错误：" + msg);
  }
}
