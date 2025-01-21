package com.example.user_tracking_demo.controller;

import com.example.user_tracking_demo.model.ConnectMessage;
import com.example.user_tracking_demo.model.TotalUsers;
import com.example.user_tracking_demo.cache.UsersCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class UsersController {

    private final UsersCache userCache;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public UsersController(UsersCache userCache, SimpMessagingTemplate messagingTemplate) {
        this.userCache = userCache;
        this.messagingTemplate = messagingTemplate;
    }

    // Mapped to /app/users
    @MessageMapping("/users")
    @SendTo("/all/users")
    public TotalUsers getAllUsers(ConnectMessage message) {
        return new TotalUsers(userCache.getAllConnectedUsers());
    }

}
