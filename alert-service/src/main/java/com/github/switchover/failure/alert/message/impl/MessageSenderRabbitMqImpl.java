package com.github.switchover.failure.alert.message.impl;

import com.github.switchover.failure.alert.message.MessageSender;
import com.github.switchover.failure.common.dto.IntegratedAlertMessage;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderRabbitMqImpl implements MessageSender {
    private final RabbitTemplate rabbitTemplate;
    private final DirectExchange directExchange;

    public MessageSenderRabbitMqImpl(RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    @Override
    public void sendMessage(IntegratedAlertMessage message) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "alert", message);
    }
}
