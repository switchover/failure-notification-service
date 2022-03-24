package com.github.switchover.failure.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IntegratedAlertMessage implements Serializable {
    private static final long serialVersionUID = -5721116514418069274L;
    private String severity;
    private String message;
    private final List<MessengerInfo> messengerInfoList = new ArrayList<>();

    public IntegratedAlertMessage() {

    }

    public IntegratedAlertMessage(String severity, String message) {
        this.severity = severity;
        this.message = message;
    }

    public void addMessengerInfo(String serviceType, String userToken, String target) {
        messengerInfoList.add(new MessengerInfo(serviceType, userToken, target));
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

    public List<MessengerInfo> getMessengerInfoList() {
        return messengerInfoList;
    }
}

