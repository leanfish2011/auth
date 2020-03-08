package com.tim.auth.vo;

import java.util.List;
import lombok.Data;

/**
 * @author：tim
 * @date：20-3-1 下午4:34
 * @description：
 */
@Data
public class RoleUserAdd {

  private String roleId;

  private List<String> userIdList;
}
