package com.tim.auth.vo;

import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author：Tim
 * @date：2017年8月27日 下午6:58:52
 * @description：搜索用户参数
 */
@Data
public class UserSearchReq {

  private String name;

  private String userCode;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date beginTime;

  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date endTime;

  private String email;

}
