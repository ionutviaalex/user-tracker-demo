package com.example.user_tracking_demo.model;

public class ConnectMessage {
    private String uuid;

    public ConnectMessage() {}

    public ConnectMessage(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "ConnectMessage{" +
                "uuid='" + uuid + '\'' +
                '}';
    }
}
