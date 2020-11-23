package com.node.detection.controller;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/ws")
public class WebSocketController {
    @Autowired
    private WebSocketClient webSocketClient;

    @PostMapping("/testWs")
    public void test(){
        webSocketClient.send("{\"command\":\"ask\",\"source\":\"web\",\"password\":\"\",\"value\":\"1\"}");
    }
}
