package com.tim.auth.vo;

import java.util.Date;
import lombok.Data;

/**
 * @author：Tim
 * @date：2017年8月27日 下午6:58:52
 * @description：搜索用户参数
 */
@Data
public class UserSearchReq {

  private String name;
  private String userCode;
  private Date beginTime;
  private Date endTime;
  private String email;

}
