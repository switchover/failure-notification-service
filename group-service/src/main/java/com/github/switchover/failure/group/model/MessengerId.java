package com.github.switchover.failure.group.model;

import java.io.Serializable;

public class MessengerId implements Serializable {
    private static final long serialVersionUID = 9137736149009559225L;

    private Integer userId;
    private String serviceType;

    public MessengerId() {
    }

    public MessengerId(Integer userId, String serviceType) {
        this.userId = userId;
        this.serviceType = serviceType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MessengerId that = (MessengerId) o;

        if (!userId.equals(that.userId)) {
            return false;
        }
        return serviceType.equals(that.serviceType);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + serviceType.hashCode();
        return result;
    }
}
