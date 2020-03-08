package com.tim.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.tim"})
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.tim.auth.dao")
public class AuthApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthApplication.class, args);
  }

}
