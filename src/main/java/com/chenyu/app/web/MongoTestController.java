package com.chenyu.app.web;

import com.chenyu.app.entity.Person;
import com.chenyu.app.mongoService.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

  @RequestMapping("/save")
  public void update(@RequestBody Person person) {
    mongoService.save(person);
  }

  @RequestMapping("/test")
  public void test() {
    mongoService.aggregate3();
  }

  @RequestMapping("/test4")
  public void test4() {
    mongoService.aggregate4();
  }
}
