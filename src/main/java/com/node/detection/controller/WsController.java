package com.node.detection.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ServerEndpoint 这个注解用于标识作用在类上
 * 它的主要功能是把当前类标识成一个 WebSocket 的服务端
 * 注解的值用户客户端连接访问的 URL 地址
 */
@Slf4j
@Component
@ServerEndpoint("/ws")
public abstract class WsController {

//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    /**
     * concurrent 包的线程安全 Set，用来存放每个客户端对应的 WebSocket 对象。
     */
    private final CopyOnWriteArraySet<WsController> webSocketSet = new CopyOnWriteArraySet<>();

    /**
     *  与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) throws IOException, EncodeException {
        this.session = session;
        webSocketSet.add(this);
//        log.info("session"+session+";   author:"+authentication+" websocket连接");
        sendMessage("连接成功");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
//        log.info("session"+session+";   author:"+authentication+" websocket连接关闭");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param queryType 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String queryType) throws IOException, EncodeException {
        log.info("收到信息:" + queryType);
        sendMessage("hello");
    }

    /**
     * @param session 当前对话
     * @param error 错误信息
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送，单发消息
     *
     * @param message 想要发送的消息
     * @throws IOException     发送失败
     * @throws EncodeException Object 转 JSON 失败
     */
    public void sendMessage(Object message) throws IOException, EncodeException {
        this.session.getBasicRemote().sendObject(message);
    }

    /**
     * 实现服务器主动群发消息
     *
     * @param message 想要发送的消息
     * @throws IOException     发送失败
     * @throws EncodeException Object 转 JSON 失败
     */
    public void sendInfo(Object message) throws IOException, EncodeException {
        log.info("websocket群发消息，消息：" + message);
        for (WsController item : webSocketSet) {
            item.sendMessage(message);
        }
    }
}
