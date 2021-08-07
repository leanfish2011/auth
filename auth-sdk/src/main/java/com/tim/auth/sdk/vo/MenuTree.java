package com.tim.auth.sdk.vo;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @author：Tim
 * @date：2017年7月30日 下午6:17:14
 * @description：后台管理菜单
 */
@Data
public class MenuTree {

  //private MenuResp root;// 根节点
  private String id;
  private String name;
  private String url;
  private String requestPath;
  private String parentId;
  private List<MenuTree> children = new ArrayList<>();// 子节点，子节点还存着子节点

  public MenuTree() {
  }

  public MenuTree(MenuResp menuResp) {
    this.id = menuResp.getId();
    this.name = menuResp.getName();
    this.url = menuResp.getUrl();
    this.requestPath = menuResp.getRequestPath();
    this.parentId = menuResp.getParentId();
  }

}
