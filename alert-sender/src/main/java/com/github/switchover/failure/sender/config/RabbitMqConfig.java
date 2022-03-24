package com.github.switchover.failure.sender.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue alertQueue() {
        return new Queue("alert", false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("direct-exchange");
    }

    @Bean
    public Binding alertBinding(DirectExchange alertExchange, Queue alertQueue) {;
        return BindingBuilder.bind(alertQueue).to(alertExchange).with("alert");
    }
}
