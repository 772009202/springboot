package com.chenyu.app.web;

import com.chenyu.app.mongoService.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenyu
 * @date 2021-11-16
 */
@RestController
@RequestMapping("/mongo")
public class MongoTestController {

  @Autowired private MongoService mongoService;

  @RequestMapping("/test")
  public void test() {
    mongoService.aggregate3();
  }
}
