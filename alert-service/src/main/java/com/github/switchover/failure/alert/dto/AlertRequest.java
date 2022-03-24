package com.github.switchover.failure.alert.dto;

import java.util.List;

public class AlertRequest {
    private List<String> target;
    private String severity;
    private String message;

    public List<String> getTarget() {
        return target;
    }

    public void setTarget(List<String> target) {
        this.target = target;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
