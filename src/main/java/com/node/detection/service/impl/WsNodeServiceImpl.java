package com.node.detection.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.node.detection.entity.mongo.LastNode;
import com.node.detection.entity.mongo.Node;
import com.node.detection.entity.ws.WsNode;
import com.node.detection.service.LastNodeService;
import com.node.detection.service.NodeService;
import com.node.detection.service.WebSocketService;
import com.node.detection.service.WsNodeService;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class WsNodeServiceImpl implements WsNodeService {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private LastNodeService lastNodeService;

    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private WebSocketClient webSocketClient;


    public void dealWithWebsocketMessage(String message) throws IOException, EncodeException {
        // 转为 Java 对象
        WsNode wsNode = stringToWsNodeBean(message);
        if ("recv".equals(wsNode.getCommand())){
            wsNode.setCommand("ask");
            webSocketClient.send(wsNode.toString());
        }
        if ("reAsk".equals(wsNode.getCommand())) {
            Node node = wsNodeDataToNode(wsNode.getData());
            lastNodeService.saveLastNode(nodeToLastNode(node));
            nodeService.saveNode(node);
            webSocketService.sendInfo(node.toString());
        }
    }

    /**
     * WebSocket 接收的是 String 类型 ，需要转为我们需要的 Java 对象
     *
     * @param str String 类型的 JSON 数据
     * @return WsNode 对象
     */
    private WsNode stringToWsNodeBean(String str) {
        // 先解析为 json，再转为 Java 对象
        return JSONUtil.toBean(JSONUtil.parseObj(str), WsNode.class);
    }

    private LastNode nodeToLastNode(Node node) {
        LastNode lastNode = JSONUtil.toBean(JSONUtil.parseObj(node), LastNode.class);
        lastNode.setLastFullTime(node.getDistance() == 0.9 ? DateUtil.now() : "0");
        lastNode.setLastCleanTime("0");
        lastNode.setOnFire(node.getSmog() == 1 && node.getEnvTemp() > 60);
        return lastNode;
    }

    /**
     * 把 WebSocket 的对象格式转为我们所需要的数据格式 -> Node
     *
     * @param data WsNode 中的 data 列表
     * @return Node 信息
     */
    private Node wsNodeDataToNode(List<WsNode.MyData> data) {
        Node node = new Node();
        for (WsNode.MyData i : data) {
            String name = i.getName();
            String value = i.getValue();
            switch (name) {
                case "IMSI":
                    node.setIMSI(value);
                    break;
                case "serverIP":
                    node.setServerIP(value);
                    break;
                case "serverPort":
                    node.setServerPort(value);
                    break;
                case "currentTime":
                    node.setCurrentTime(value);
                    break;
                case "sendFrequencySec":
                    node.setSendFrequencySec(Integer.parseInt(value));
                    break;
                case "IMEI":
                    node.setIMEI(value);
                    break;
                case "bright":
                    node.setBright(Integer.parseInt(value));
                    break;
                case "distance":
                    node.setDistance(Integer.parseInt(value));
                    break;
                case "smog":
                    node.setSmog(Integer.parseInt(value));
                    break;
                case "envTemp":
                    node.setEnvTemp(Integer.parseInt(value));
                    break;
                case "lbs_location":
                    node.setLbsLocation(value);
                    break;

            }
        }
        return node;
    }
}
