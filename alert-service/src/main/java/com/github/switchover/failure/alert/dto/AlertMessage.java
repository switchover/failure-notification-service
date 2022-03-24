package com.github.switchover.failure.alert.dto;

import java.io.Serializable;

public class AlertMessage implements Serializable {
    private final String severity;
    private final String message;
    private final String userToken;
    private final String target;

    public AlertMessage(String severity, String message, String userToken, String target) {
        this.severity = severity;
        this.message = message;
        this.userToken = userToken;
        this.target = target;
    }

    public String getSeverity() {
        return severity;
    }

    public String getMessage() {
        return message;
    }

    public String getUserToken() {
        return userToken;
    }

    public String getTarget() {
        return target;
    }
}
