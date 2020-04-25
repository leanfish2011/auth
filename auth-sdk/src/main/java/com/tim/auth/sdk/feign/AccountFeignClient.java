package com.tim.auth.sdk.feign;

import com.tim.auth.sdk.config.FeignConfiguration;
import com.tim.auth.sdk.vo.TokenModel;
import com.tim.message.Message;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${auth.server.name:auth-server}", path = "/api/v1/auth/access", configuration = FeignConfiguration.class)
public interface AccountFeignClient {

  @RequestMapping(value = "/check", method = RequestMethod.GET)
  Message check();

  @RequestMapping(value = "/permission", method = RequestMethod.GET)
  Message permission(@RequestParam(value = "uri") String uri,
      @RequestParam(value = "method") String method);

  @RequestMapping(value = "/profile", method = RequestMethod.GET)
  Message<TokenModel> profile();
}
