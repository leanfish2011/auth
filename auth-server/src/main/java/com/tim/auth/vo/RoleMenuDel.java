package com.tim.auth.vo;

import java.util.List;
import lombok.Data;

/**
 * @author：tim
 * @date：20-3-1 下午5:13
 * @description：
 */
@Data
public class RoleMenuDel {

  private String roleId;

  private List<String> menuIdList;
}
