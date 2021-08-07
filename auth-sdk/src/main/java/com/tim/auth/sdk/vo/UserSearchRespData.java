package com.tim.auth.sdk.vo;

import java.util.List;
import lombok.Data;

/**
 * @author：tim
 * @date：20-7-4 上午9:49
 * @description：
 */
@Data
public class UserSearchRespData {

  private Integer allTotal;

  private Integer currentTotal;

  private List<UserSearchResp> list;
}
