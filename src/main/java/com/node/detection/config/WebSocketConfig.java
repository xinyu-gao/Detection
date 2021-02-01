package com.node.detection.config;

import com.node.detection.service.WsNodeService;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.net.URI;


/**
 * @author xinyu
 */
@Configuration
@Slf4j
public class WebSocketConfig {

    @Value("${websocket.uri}")
    private String uri;

    @Autowired
    private WsNodeService wsNodeService;

    @Autowired
    private WebSocketClient webSocketClient;

    private boolean wsFirstConnect = true;

    /**
     * 这个 Bean 会自动注册使用 @ServerEndpoint 注解声明的 websocket endpoint
     *
     * @return ServerEndpointExporter 对象
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    @Bean
    public WebSocketClient webSocketClient() {

        try {
            WebSocketClient webSocketClient = new WebSocketClient(new URI(uri), new Draft_6455()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    if (wsFirstConnect) {
                        log.info("[websocket] 连接成功 = {}", uri);
                        wsFirstConnect = false;
                    }
                }

                @Override
                public void onMessage(String message) {
                    try {
                        wsNodeService.dealWithWebsocketMessage(message);
                    } catch (IOException | EncodeException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    //  log.info("[websocket] 退出连接, {}", uri);
                }

                @Override
                public void onError(Exception ex) {
//                    log.info("[websocket] 连接错误 = {}",ex.getMessage());
                }
            };
            webSocketClient.connect();
            return webSocketClient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void reconnectWebSocket() {
        webSocketClient.reconnect();
    }

}