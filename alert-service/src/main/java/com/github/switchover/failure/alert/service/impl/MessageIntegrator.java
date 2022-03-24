package com.github.switchover.failure.alert.service.impl;

import com.github.switchover.failure.common.dto.IntegratedAlertMessage;
import com.github.switchover.failure.common.dto.MessengerInfo;

import java.util.ArrayList;
import java.util.List;

public class MessageIntegrator {
    private final int messageSize;
    private String severity = "normal";
    private String message = "";
    private final List<MessengerInfo> messengerInfoList = new ArrayList<>();

    public MessageIntegrator(int messageSize) {
        this.messageSize = messageSize;
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

    public void addMessengerInfo(String serviceType, String userToken, String target) {
        messengerInfoList.add(new MessengerInfo(serviceType, userToken, target));
    }

    public List<IntegratedAlertMessage> getIntegratedMessage() {
        List<IntegratedAlertMessage> result = new ArrayList<>();

        // Multiple messages are grouped into a fixed size(message size).
        int count = 0;
        IntegratedAlertMessage toBeSentMessage = new IntegratedAlertMessage(severity, message);
        for (MessengerInfo info : messengerInfoList) {
            // First, add one message.
            toBeSentMessage.addMessengerInfo(info.getServiceType(), info.getUserToken(), info.getTarget());

            // Second, when the message size is reached, a new message group is constructed.
            if (++count >= messageSize) {
                count = 0;
                result.add(toBeSentMessage);
                toBeSentMessage = new IntegratedAlertMessage(severity, message);
            }
        }

        // Finally, the remaining messages that have not reached the message size are grouped.
        if (count > 0) {
            result.add(toBeSentMessage);
        }

        return result;
    }

}
