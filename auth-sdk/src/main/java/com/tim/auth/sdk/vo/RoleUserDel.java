package com.tim.auth.sdk.vo;

import java.util.List;
import lombok.Data;

/**
 * @author：tim
 * @date：20-3-1 下午5:12
 * @description：
 */
@Data
public class RoleUserDel {

  private String roleId;

  private List<String> userIdList;
}
