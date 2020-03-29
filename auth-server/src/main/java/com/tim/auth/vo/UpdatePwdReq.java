package com.tim.auth.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author：tim
 * @date：20-3-29 上午11:45
 * @description：修改密码
 */
@Data
public class UpdatePwdReq {

  @NotBlank
  private String id;

  @NotBlank
  private String oldPassword;

  @NotBlank
  private String newPassword;

}
