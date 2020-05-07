package com.tim.auth.runner;

import com.tim.auth.component.LoadResourceRole;
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
  private LoadResourceRole loadResourceRole;

  @Override
  public void run(String... strings) {
    // 加载权限进redis
    loadResourceRole.load();
  }
}
