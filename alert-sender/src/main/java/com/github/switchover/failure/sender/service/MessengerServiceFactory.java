package com.github.switchover.failure.sender.service;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessengerServiceFactory {
    private final Map<MessengerType, MessengerService> messengerServices = new HashMap<>();

    // Several external service implementations(eg: SlackMessengerServiceImpl) are automatically injected
    public MessengerServiceFactory(List<MessengerService> messengerServices) {
        if (CollectionUtils.isEmpty(messengerServices)) {
            throw new IllegalArgumentException("There is no messenger service.");
        }

        for (MessengerService messengerService : messengerServices) {
            this.messengerServices.put(messengerService.getMessengerType(), messengerService);
        }
    }

    public MessengerService getService(MessengerType messengerType) {
        return messengerServices.get(messengerType);
    }
}
