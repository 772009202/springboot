package com.chenyu.app.util;

import com.chenyu.app.config.Constants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author chenyu
 * @date 2020-08-28
 */
@Component
public class MqProducer {

  @Autowired private RabbitTemplate rabbitTemplate;

  public void send() {
    String sendMsg = "hello1 " + new Date();
    rabbitTemplate.convertAndSend(Constants.CHENYU_EXCHANGE, sendMsg);
  }
}
