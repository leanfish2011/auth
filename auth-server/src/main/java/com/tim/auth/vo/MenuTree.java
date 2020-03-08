package com.tim.auth.vo;

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

  private MenuResp root;// 根节点
  private List<MenuTree> children = new ArrayList<>();// 子节点，子节点还存着子节点

  public MenuTree() {
  }

  public MenuTree(MenuResp root) {
    this.root = root;
  }

}
