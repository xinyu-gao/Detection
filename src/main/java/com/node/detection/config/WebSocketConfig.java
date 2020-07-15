package com.node.detection.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * @author xinyu
 */
@Configuration
public class WebSocketConfig {

    /**
     * 这个 Bean 会自动注册使用 @ServerEndpoint 注解声明的 websocket endpoint
     * @return ServerEndpointExporter 对象
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}