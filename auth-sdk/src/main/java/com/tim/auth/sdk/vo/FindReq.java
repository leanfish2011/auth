package com.tim.auth.sdk.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author：tim
 * @date：2022-12-18 4:33 PM
 * @description：忘记密码查找
 */
@Data
public class FindReq {

  @NotBlank(message = "用户名不能为空")
  private String userCode;

  @NotBlank(message = "邮箱不能为空")
  private String email;
}
