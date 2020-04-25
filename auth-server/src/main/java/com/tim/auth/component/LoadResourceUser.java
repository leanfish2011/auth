package com.tim.auth.component;

import com.tim.auth.ao.ResourceUser;
import com.tim.auth.service.AccessService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author：tim
 * @date：20-4-11 下午6:04
 * @description：加载权限和用户关系到redis
 */
@Component
@Slf4j
public class LoadResourceUser {

  @Autowired
  private AccessService accessService;

  @Autowired
  private ResourceManager resourceManager;

  public void load() {
    List<ResourceUser> lstResourceUser = accessService.loadRequestResouce();
    resourceManager.loadResource(lstResourceUser);
    log.info("加载权限和用户关系到redis");
  }
}
