package com.example.user_tracking_demo.cache;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UsersCache {
    public AtomicInteger connectedUsers = new AtomicInteger(0);

    public int getAllConnectedUsers() {
        return connectedUsers.get();
    }

    public int userConnected() {
        return connectedUsers.incrementAndGet();
    }

    public int userDisconnected() {
        // We can't have less than 0 connected users.
        return connectedUsers.updateAndGet(nr -> nr > 0 ? nr -1 : nr);
    }
}
