package com.github.switchover.failure.common.dto;

import java.io.Serializable;

public class MessengerInfo implements Serializable {
    private static final long serialVersionUID = 8277592276050265719L;
    private String serviceType;
    private String userToken;
    private String target;

    public MessengerInfo() {

    }

    public MessengerInfo(String serviceType, String userToken, String target) {
        this.serviceType = serviceType;
        this.userToken = userToken;
        this.target = target;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
