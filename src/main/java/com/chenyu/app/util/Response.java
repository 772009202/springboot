package com.chenyu.app.util;

import java.io.Serializable;

/**
 * @author chenyu
 * @date 2020-09-01
 */
public class Response<T> implements Serializable {

  private static final long serialVersionUID = 0L;

  private int code;

  private String message;

  private T data;

  public Response(int code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public static Response success(String message) {
    return new Response(1, message, null);
  }

  public static Response success(Object obj) {
    return new Response(1, "", obj);
  }

  public static <T> Response<T> success(String message, T t) {
    return new Response(1, message, t);
  }

  public static Response fail(String message) {
    return new Response(-1, message, null);
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getObj() {
    return data;
  }

  public void setObj(T obj) {
    this.data = obj;
  }
}
