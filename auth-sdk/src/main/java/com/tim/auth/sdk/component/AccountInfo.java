package com.tim.auth.sdk.component;

import com.tim.auth.sdk.vo.TokenModel;
import com.tim.exception.type.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author：tim
 * @date：20-4-11 上午11:27
 * @description： 存储当前用户信息
 */
@Component
@Lazy
@Slf4j
public class AccountInfo {

  private TokenModel tokenModel;

  public TokenModel getUserInfo() {
    if (tokenModel == null) {
      log.warn("未找到用户");
      throw new NotFoundException("未找到用户");
    }

    return tokenModel;
  }

  public void setTokenModel(TokenModel tokenModel) {
    this.tokenModel = tokenModel;
  }

}
