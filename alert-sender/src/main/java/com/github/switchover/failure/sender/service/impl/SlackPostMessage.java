package com.github.switchover.failure.sender.service.impl;

import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class SlackPostMessage {
    static void postMessage(String token, String target, String message, RestTemplate restTemplate, String slackUrl, String slackApi) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bear " + token); // fixme bearer?

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("channel", target);
        jsonObject.put("text", message);

        HttpEntity<String> request =
            new HttpEntity<>(jsonObject.toJSONString(), headers);

        restTemplate.postForObject(slackUrl + slackApi, request, String.class);
    }
}
