package com.example.user_tracking_demo.listener;

import com.example.user_tracking_demo.cache.UsersCache;
import com.example.user_tracking_demo.model.TotalUsers;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketListener {

    private final UsersCache userCache;
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketListener(UsersCache userCache, SimpMessagingTemplate messagingTemplate) {
        this.userCache = userCache;
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    @SendTo("/all/users")
    public void handleSessionConnected(SessionConnectEvent event) {
        System.out.println("User connected...");
        int totalUsers = userCache.userConnected();
        messagingTemplate.convertAndSend("/all/users", new TotalUsers(totalUsers));
        System.out.println("Broadcast [" + totalUsers + "] connected users.");
    }

    @EventListener
    @SendTo("/all/users")
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        System.out.println("User disconnected...");
        int totalUsers = userCache.userDisconnected();
        messagingTemplate.convertAndSend("/all/users", new TotalUsers(totalUsers));
        System.out.println("Broadcast [" + totalUsers + "] connected users.");
    }
}
