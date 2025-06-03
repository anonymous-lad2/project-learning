package com.happysat.notification_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * WebSocket Notification Controller
 *
 * This controller handles real-time messaging using STOMP over WebSocket.
 * It receives messages from clients and broadcasts them to all connected subscribers.
 *
 * Key Responsibilities:
 * - Receive messages from clients via WebSocket
 * - Broadcast messages to all subscribed clients
 * - Maintain real-time communication channel
 *
 * Flow:
 * 1. Client sends message to "/app/sendMessage"
 * 2. Controller receives and processes the message
 * 3. Message is broadcast to all clients subscribed to "/topic/notification"
 */
@Controller // Marks this class as a Spring MVC Controller
public class NotificationController {

    /**
     * SimpMessagingTemplate - Core Spring class for sending WebSocket messages
     *
     * Features:
     * - Provides methods for sending messages to destinations
     * - Handles message conversion and routing
     * - Supports publish-subscribe messaging pattern
     */
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * Constructor-based dependency injection
     *
     * @param messagingTemplate Autowired by Spring, used for message broadcasting
     */
    @Autowired
    public NotificationController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Handles incoming WebSocket messages and broadcasts to all clients
     *
     * Message Flow:
     * 1. Client sends message to "/app/sendMessage"
     * 2. This method receives the message
     * 3. Broadcasts message to "/topic/notification"
     *
     * @param message The message content received from client (auto-converted from JSON if needed)
     */
    @MessageMapping("/sendMessage") // Maps WebSocket messages sent to "/app/sendMessage" to this method
    public void sendMessage(String message) {
        // Log the received message (for debugging and monitoring)
        System.out.println("Received message: " + message);

        /**
         * Broadcast the message to all subscribers of "/topic/notification"
         *
         * convertAndSend() does three things:
         * 1. Converts the message object to proper format (JSON by default)
         * 2. Sends to the specified destination ("/topic/notification")
         * 3. Delivers to all connected clients subscribed to this topic
         */
        messagingTemplate.convertAndSend("/topic/notification", message);
    }
}