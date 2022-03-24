package com.github.switchover.failure.sender.message;

import com.github.switchover.failure.common.dto.IntegratedAlertMessage;
import com.github.switchover.failure.common.dto.MessengerInfo;
import com.github.switchover.failure.sender.service.MessengerService;
import com.github.switchover.failure.sender.service.MessengerServiceFactory;
import com.github.switchover.failure.sender.service.MessengerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class MessageReceiverRabbitMq {
    private final Logger logger = LoggerFactory.getLogger(MessageReceiverRabbitMq.class);

    private CountDownLatch latch = new CountDownLatch(1);

    private final MessengerServiceFactory serviceFactory;

    public MessageReceiverRabbitMq(MessengerServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    @RabbitListener(queues = "alert")
    public void receiveMessage(IntegratedAlertMessage message) {
        logger.info("Received <{}>", message.getMessage());

        for (MessengerInfo info : message.getMessengerInfoList()) {
            MessengerService service = serviceFactory.getService(MessengerType.valueOf(info.getServiceType().toUpperCase()));

            String contents = String.format("[%s] %s", message.getSeverity(), message.getMessage());
            service.send(info.getUserToken(), info.getTarget(), contents);
        }

        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
