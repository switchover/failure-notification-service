package com.github.switchover.failure.alert.rest;

import com.github.switchover.failure.alert.dto.AlertRequest;
import com.github.switchover.failure.alert.dto.AlertResponse;
import com.github.switchover.failure.alert.service.AlertService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/alerts")
public class AlertController {
    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public AlertResponse requestSendingAlerts(@RequestBody AlertRequest request) {
        AlertResponse response = new AlertResponse();

        int users = alertService.sendAlerts(request);
        response.setUserCount(users);

        return response;
    }
}
