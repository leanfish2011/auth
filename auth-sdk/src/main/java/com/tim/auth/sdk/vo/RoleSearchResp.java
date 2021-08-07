package com.tim.auth.sdk.vo;

import java.util.Date;
import lombok.Data;

/**
 * @author：Tim
 * @date：2018年1月23日 下午9:59:18
 * @description：角色查询返回结果
 */
@Data
public class RoleSearchResp {

  private String id;
  private String name;
  private Date createTime;
  private Date modifyTime;
  private String remark;
}
