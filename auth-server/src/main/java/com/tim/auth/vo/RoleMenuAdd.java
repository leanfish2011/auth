package com.tim.auth.vo;

import java.util.List;
import lombok.Data;

/**
 * @author：tim
 * @date：20-3-1 下午4:50
 * @description：
 */
@Data
public class RoleMenuAdd {

  private String roleId;

  private List<String> menuIdList;
}
