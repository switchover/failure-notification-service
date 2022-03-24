package com.github.switchover.failure.alert.service;

import com.github.switchover.failure.alert.dto.AlertRequest;

public interface AlertService {
    int sendAlerts(AlertRequest request);
}
