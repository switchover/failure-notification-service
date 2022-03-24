package com.github.switchover.failure.sender.service.impl;

import com.github.switchover.failure.sender.service.MessengerService;
import com.github.switchover.failure.sender.service.MessengerType;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Profile("resilience")
@Service
public class SlackMessengerServiceResilienceImpl implements MessengerService {
    private final Logger logger = LoggerFactory.getLogger(SlackMessengerServiceResilienceImpl.class);

    private static final RestTemplate restTemplate = new RestTemplate();

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
    @CircuitBreaker(name = "messenger", fallbackMethod = "sendFallback")
    @RateLimiter(name = "messenger")
    public void send(String token, String target, String message) {
        SlackPostMessage.postMessage(token, target, message, restTemplate, slackUrl, slackApi);
    }

    private void sendFallback(Throwable ex) {
        logger.error("fallback", ex);
    }
}
