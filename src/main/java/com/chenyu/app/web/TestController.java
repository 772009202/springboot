package com.chenyu.app.web;

import com.chenyu.app.dao.PersonRepository;
import com.chenyu.app.entity.Person;
import com.chenyu.app.service.IUserService;
import com.chenyu.app.util.MqProducer;
import com.chenyu.app.util.RedisUtils;
import com.chenyu.app.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author chenyu
 * @date 2020-08-28
 */
@RestController
@Api(description = "测试类")
public class TestController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired private PersonRepository personRepository;

  @Autowired private MqProducer mqProducer;

  @Autowired private RedisUtils redisUtils;

  @Autowired private IUserService userService;

  @GetMapping("test")
  @ResponseBody
  public List<Person> get() {
    logger.info("你好呀");
    return personRepository.findAll();
  }

  @GetMapping("send")
  public void send() {
    logger.info("开始发送消息");
    mqProducer.send();
  }

  @GetMapping("redis")
  public void redis() {
    logger.info("redis存值");
    redisUtils.setCacheObject("chenyu", "test", 200, TimeUnit.SECONDS);
  }

  @GetMapping("mybatis")
  public Response<List> mybatis(@RequestParam("userName") String userName) {
    Map param = new HashMap();
    param.put("username", userName);
    return Response.success("成功", userService.listByMap(param));
  }

  /**
   * http://127.0.0.1:8081/swagger-ui.html swagger的地址
   *
   * @param id
   * @param person
   */
  @ApiOperation(value = "测试swagger")
  @ApiImplicitParams({
    @ApiImplicitParam(
        paramType = "query",
        name = "id",
        dataType = "Long",
        required = true,
        value = "id")
  })
  @PostMapping("swagger")
  public void swagger(@RequestParam String id, @RequestBody Person person) {
    System.out.println("跑过了");
  }
}
