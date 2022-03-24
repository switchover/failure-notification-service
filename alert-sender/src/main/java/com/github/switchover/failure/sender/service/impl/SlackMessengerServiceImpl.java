package com.github.switchover.failure.sender.service.impl;

import com.github.switchover.failure.sender.service.MessengerService;
import com.github.switchover.failure.sender.service.MessengerType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Profile("!resilience")
@Service
public class SlackMessengerServiceImpl implements MessengerService {
    private static RestTemplate restTemplate = new RestTemplate();

    @Value("${slack.url}")
    private String slackUrl;
    @Value("${slack.api}")
    private String slackApi;

    @Override
    public MessengerType getMessengerType() {
        return MessengerType.SLACK;
    }

    @Override
    public String getContentType() {
        return "application/json";
    }

    @Override
    public void send(String token, String target, String message) {
        SlackPostMessage.postMessage(token, target, message, restTemplate, slackUrl, slackApi);
    }
}
