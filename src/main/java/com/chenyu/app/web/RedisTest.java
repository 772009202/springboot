package com.chenyu.app.web;

import com.chenyu.app.entity.User;
import com.chenyu.app.util.RedisUtils;
import com.chenyu.app.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 关于token的安全性：<br>
 * 1.使用https请求保证不被抓包<br>
 * 2.token注意时效性，防止token的滥用<br>
 * 3.token里最好带上用id设备id等信息采用hash算法服务端验签防止篡改<br>
 *
 * @author chenyu
 * @date 2020-10-22
 */
@RestController
@RequestMapping("redis")
public class RedisTest {
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  @Autowired private RedisUtils redisUtils;

  @GetMapping("/set")
  public void setValue() {
    User user = new User();
    user.setId(2L);
    user.setPassword("redisTest");
    user.setUsername("test");
    redisUtils.setCacheObject(user.getUsername(), user, 5 * 60 * 1000, TimeUnit.SECONDS);
  }

  @GetMapping("/get")
  public Response getValue() {
    User user = redisUtils.getCacheObject("test");
    return Response.success(user);
  }
}
