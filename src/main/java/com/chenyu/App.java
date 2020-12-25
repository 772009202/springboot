package com.chenyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenyu
 * @date 2020-08-28
 */
@SpringBootApplication
@MapperScan("com.chenyu.app.mapper")
public class App {

  public static void main(String[] args) {
      SpringApplication.run(App.class);
  }

}
