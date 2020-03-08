package com.tim.auth.runner;

import com.tim.auth.ao.ResourceUser;
import com.tim.auth.component.ResourceManager;
import com.tim.auth.service.AccessService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author：tim
 * @date：20-3-1 下午11:10
 * @description：系统启动时做些事情： 1、加载权限到内存中
 */
@Component
@Order(value = 1)
public class StartupRunner implements CommandLineRunner {

  @Autowired
  private AccessService accessService;

  @Autowired
  private ResourceManager resourceManager;

  @Override
  public void run(String... strings) throws Exception {
    // 加载权限进redis
    List<ResourceUser> lstResourceUser = accessService.loadRequestResouce();
    resourceManager.loadResource(lstResourceUser);
  }
}
