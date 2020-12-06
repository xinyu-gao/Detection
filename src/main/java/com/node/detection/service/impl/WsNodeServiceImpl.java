package com.node.detection.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.node.detection.entity.mongo.IMSIInfo;
import com.node.detection.entity.mongo.Node;
import com.node.detection.entity.ws.WsNode;
import com.node.detection.service.IMSIService;
import com.node.detection.service.NodeService;
import com.node.detection.service.WebSocketService;
import com.node.detection.service.WsNodeService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.List;

@Service
public class WsNodeServiceImpl implements WsNodeService {

    @Autowired
    private NodeService nodeService;

    @Autowired
    private WebSocketService webSocketService;

    @Autowired
    private IMSIService imsiService;

    public void dealWithWebsocketMessage(String message) throws IOException, EncodeException {
        // 转为 Java 对象
        WsNode wsNode = stringToWsNodeBean(message);
        // WsNode 转为 Node 对象
        Node node = wsNodeDataToNode(wsNode.getData());
        // imsi 存储到 imsi 集合中，保存到 mongodb 中
        imsiService.insertIMSI(new IMSIInfo(node.getIMSI(), node.getCurrentTime()));
        // 存储 Node 信息到 MongoDB 数据库
        nodeService.saveNode(node);
        // 将 Node 信息传输到 Web 端
        webSocketService.sendInfo(node);
    }

    /**
     * WebSocket 接收的是 String 类型 ，需要转为我们需要的 Java 对象
     *
     * @param str String 类型的 JSON
     * @return WsNode 对象
     */
    private WsNode stringToWsNodeBean(String str) {
        // 解析为 json
        JSONObject jsonObject = JSONUtil.parseObj(str);
        // json 转为 java 对象
        return JSONUtil.toBean(jsonObject, WsNode.class);
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
                case "signalPower":
                    node.setSignalPower(Integer.parseInt(value));
                    break;
                case "bright":
                    node.setBright(Integer.parseInt(value));
                    break;
                case "touchNum":
                    node.setTouchNum(Integer.parseInt(value));
                    break;
                case "lbs_location":
                    node.setLbsLocation(value);
                    break;

            }
        }
        return node;
    }
}
