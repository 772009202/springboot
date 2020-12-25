package com.chenyu.app.config.exception;

import com.alibaba.fastjson.JSONArray;
import com.chenyu.app.util.Response;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全部异常同一处理
 *
 * @author chenyu
 * @date 2020-10-23
 */
@ControllerAdvice
public class MyException {

  /**
   * 处理controller异常
   *
   * @param req
   * @param resp
   * @param e
   * @throws Exception
   */
  @ExceptionHandler(value = AuthenticationException.class)
  public void defaultErrorHandler(HttpServletRequest req, HttpServletResponse resp, Exception e) {
    Response reqBean = new Response(500, "程序崩溃啦", e.getMessage());
    writeJson(reqBean, resp);
  }

  /** 输出JSON */
  private void writeJson(Response reqBean, HttpServletResponse response) {
    PrintWriter out = null;
    try {
      response.setCharacterEncoding("UTF-8");
      response.setContentType("application/json; charset=utf-8");
      out = response.getWriter();
      out.write(JSONArray.toJSONString(reqBean));
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }
}
