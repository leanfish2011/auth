package com.tim.auth.util;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;

/**
 * @author：tim
 * @date：20-4-11 下午9:33
 * @description：
 */
public class MapConvert {

  /**
   * 将字符串转换成map
   */
  public static Map<String, String> getMap(String responseEntity) {

    Map<String, String> map = new HashMap<>();
    // 以&来解析字符串
    String[] result = responseEntity.split("\\&");

    for (String str : result) {
      // 以=来解析字符串
      String[] split = str.split("=");
      // 将字符串存入map中
      if (split.length == 1) {
        map.put(split[0], null);
      } else {
        map.put(split[0], split[1]);
      }

    }
    return map;
  }

  /**
   * 通过json获得map
   */
  public static Map<String, String> getMapByJson(String responseEntity) {
    Map<String, String> map = new HashMap<>();
    // 阿里巴巴fastjson  将json转换成map
    JSONObject jsonObject = JSONObject.parseObject(responseEntity);
    for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
      String key = entry.getKey();
      // 将obj转换成string
      String value = String.valueOf(entry.getValue());
      map.put(key, value);
    }
    return map;
  }

}
