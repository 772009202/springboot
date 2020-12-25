package com.chenyu.app.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenyu
 * @date 2020-08-28
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    Queue queue() {
        return new Queue(Constants.CHENYU_QUEUE, true);
    }
    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange(Constants.CHENYU_EXCHANGE);
    }
    @Bean
    Binding binding(Queue queue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

}
