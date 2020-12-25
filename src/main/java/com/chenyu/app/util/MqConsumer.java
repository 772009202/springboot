package com.chenyu.app.util;

/**
 * @author chenyu
 * @date 2020-08-28
 */
import com.chenyu.app.config.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = Constants.CHENYU_QUEUE)
public class MqConsumer {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1  : " + hello);
    }

}
