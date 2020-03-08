package com.tim.auth.util;

import com.alibaba.fastjson.JSON;
import com.tim.message.Message;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

/**
 * @author：tim
 * @date：20-3-1 下午9:52
 * @description：
 */
public class ResponseUtil {

  public static void responseOutWithJson(HttpServletResponse response, Message message) {
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json; charset=utf-8");
    PrintWriter out = null;
    try {
      out = response.getWriter();
      out.append(JSON.toJSONString(message));
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }
}
