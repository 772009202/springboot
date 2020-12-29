package com.chenyu.app.web;

import com.chenyu.app.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenyu
 * @date 2020-09-15
 */
@RestController
@RequestMapping("json")
@Slf4j
public class JsonTest {

  /**
   * 尽量不要在get中用json数据
   *
   * @param userName
   * @param user
   */
  @GetMapping
  public void getJson(@RequestParam("userName") String userName, @RequestBody User user) {
    log.info("\n【参数】userName:{},user:{}", userName, user.toString());
  }
}
