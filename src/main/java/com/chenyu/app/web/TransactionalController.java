package com.chenyu.app.web;

import com.chenyu.app.service.IPersonService;
import com.chenyu.app.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenyu
 * @date 2020-10-15
 */
@RestController
@RequestMapping("tran")
public class TransactionalController {

  @Autowired private IPersonService iPersonService;

  @RequestMapping("testTran")
  public Response getTransaction(@RequestParam(name = "number") int number) {
    try {
      iPersonService.testTransactional(number);
      System.out.println("2");
    } catch (Exception e) {
      System.out.println(e);
      return Response.fail(e.getMessage().substring(0, 50));
    }

    return Response.success("没有异常");
  }
}
