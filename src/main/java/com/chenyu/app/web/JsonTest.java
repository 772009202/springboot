package com.chenyu.app.web;

import com.chenyu.app.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenyu
 * @date 2020-09-15
 */
@RestController
@RequestMapping("json")
public class JsonTest {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  /**
   * 尽量不要在get中用json数据
   *
   * @param userName
   * @param user
   */
  @GetMapping
  public void getJson(@RequestParam("userName") String userName, @RequestBody User user) {
    logger.info("\n【参数】userName:{},user:{}", userName, user.toString());
  }
}
