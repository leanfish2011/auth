package com.tim.auth.sdk.feign;

import com.tim.auth.sdk.config.FeignConfiguration;
import com.tim.message.Message;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "auth-client", url = "http://127.0.0.1:9091", configuration = FeignConfiguration.class)
public interface AccountFeignClient {

  @RequestMapping(value = "/api/v1/auth/access", method = RequestMethod.GET)
  Message check();

  @RequestMapping(value = "/api/v1/auth/permission", method = RequestMethod.GET)
  Message permission();
}
