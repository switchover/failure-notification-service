package com.github.switchover.failure.group.model;

import javax.persistence.*;

@Entity
@Table(name = "users_messengers")
@IdClass(MessengerId.class)
public class Messenger {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @Column(name = "service_type")
    private String serviceType;

    @Column(name = "token")
    private String token;

    @Column(name = "target")
    private String target;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
