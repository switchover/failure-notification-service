package com.github.switchover.failure.sender.service;

public interface MessengerService {
    MessengerType getMessengerType();

    String getContentType();

    void send(String token, String target, String message);
}
