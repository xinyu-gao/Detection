package com.node.detection.service;

import com.node.detection.entity.ws.WsNode;

import javax.websocket.EncodeException;
import java.io.IOException;

public interface WsNodeService {
    void dealWithWebsocketMessage(String str) throws IOException, EncodeException;
}
