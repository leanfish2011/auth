package com.tim.auth.util;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @author：tim
 * @date：20-4-11 下午9:25
 * @description：
 */
public class HttpClientUtils {

  /**
   * 发送get请求，利用java代码发送请求
   */
  public static String doGet(String url, String authorization) throws Exception {
    CloseableHttpClient httpclient = HttpClients.createDefault();

    HttpGet httpGet = new HttpGet(url);
    if (StringUtils.isNotEmpty(authorization)) {
      httpGet.addHeader("Authorization", authorization);
    }

    // 发送了一个http请求
    CloseableHttpResponse response = httpclient.execute(httpGet);
    // 如果响应200成功,解析响应结果
    if (response.getStatusLine().getStatusCode() == 200) {
      // 获取响应的内容
      HttpEntity responseEntity = response.getEntity();

      return EntityUtils.toString(responseEntity);
    }

    return null;
  }
}
