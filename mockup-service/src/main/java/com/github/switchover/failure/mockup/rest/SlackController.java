package com.github.switchover.failure.mockup.rest;

import com.github.switchover.failure.mockup.model.SlackMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SlackController {
    @PostMapping("/chat.postMessage")
    public void postMessage(
        @RequestHeader("Content-Type") String contentType,
        @RequestHeader("Authorization") String authorization,
        @RequestBody SlackMessage slackMessage
    ) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("POST https://slack.com/api/chat.postMessage");
        System.out.println();
        System.out.println("Context-Type: " + contentType);
        System.out.println("Authorization:" + authorization);
        System.out.println();
        System.out.println("{");
        System.out.println("  \"channel\": \"" + slackMessage.getChannel() + "\"");
        System.out.println("  \"text\": \"" + slackMessage.getText() + "\"");
        System.out.println("}");
    }
}
