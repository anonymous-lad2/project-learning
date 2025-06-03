package com.happysat.notification_system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket Configuration Class
 *
 * This class configures WebSocket communication in the Spring Boot application
 * using STOMP (Simple Text Oriented Messaging Protocol) over WebSocket.
 *
 * Key Concepts:
 * - WebSocket: A protocol providing full-duplex communication over a single TCP connection
 * - STOMP: A simple messaging protocol that defines the format and rules for data exchange
 * - Message Broker: Handles routing messages from publishers to subscribers
 * - SockJS: Provides WebSocket emulation for browsers that don't support WebSocket
 */
@Configuration // Marks this class as a Spring configuration class
@EnableWebSocketMessageBroker // Enables WebSocket message handling, backed by a message broker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Configures the message broker options
     *
     * This method sets up:
     * - A simple in-memory message broker for destinations prefixed with "/topic"
     * - Application destination prefixes for messages bound to @MessageMapping methods
     *
     * @param config the MessageBrokerRegistry to configure
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Enable a simple memory-based message broker to carry messages back to the client
        // on destinations prefixed with "/topic"
        config.enableSimpleBroker("/topic");

        // Designate the "/app" prefix for messages that are bound for @MessageMapping-annotated methods
        // This prefix will be used to define all the message mappings
        config.setApplicationDestinationPrefixes("/app");

        // Note: You can configure multiple channels if needed, for example:
        // /topic/cricket for cricket updates
        // /topic/orders for order notifications
    }

    /**
     * Registers STOMP endpoints
     *
     * This method registers the "/ws" endpoint, enabling SockJS fallback options so
     * that alternate transports can be used if WebSocket is not available.
     *
     * @param registry the StompEndpointRegistry to configure
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register the "/ws" endpoint, enabling SockJS protocol
        // This is the endpoint that clients will connect to
        registry.addEndpoint("/ws")
                // Allow all origins (for development - restrict in production)
                .setAllowedOrigins("*")
                // Enable SockJS fallback options
                .withSockJS();
    }
}