package com.github.switchover.failure.alert.message;

import com.github.switchover.failure.common.dto.IntegratedAlertMessage;

public interface MessageSender {
    void sendMessage(IntegratedAlertMessage message);
}
