package com.github.switchover.failure.mockup.model;

import java.io.Serializable;

public class SlackMessage implements Serializable {
    private String channel = "";
    private String text = "";

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
