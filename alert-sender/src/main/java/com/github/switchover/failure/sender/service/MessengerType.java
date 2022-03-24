package com.github.switchover.failure.sender.service;

public enum MessengerType {
    SLACK("slack");

    private String typeName;

    MessengerType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
