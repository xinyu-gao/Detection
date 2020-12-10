package com.node.detection.service.impl;

import cn.hutool.core.date.DateUtil;
import com.node.detection.dao.NodeRepository;
import com.node.detection.entity.mongo.Node;
import com.node.detection.entity.util.LineData;
import com.node.detection.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xinyu
 */
@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NodeRepository nodeRepository;

    @Override
    @Transactional
    public Node saveNode(Node node) {
        return nodeRepository.save(node);
    }

    @Override
    public LineData getLineDataByIMSI(String imsi) {
        Page<Node> nodeList = nodeRepository.findByIMSIOrderByCurrentTimeDesc(imsi , PageRequest.of(0, 20));
        List<String> timeList = new LinkedList<>();
        List<Integer>  lineMcuTempList = new LinkedList<>();
        List<Integer> lineSignalPowerList = new LinkedList<>();
        List<Integer> lineBrightList = new LinkedList<>();
        for(Node node: nodeList.getContent()){
            timeList.add(0, node.getCurrentTime().substring(11, 16));
            lineMcuTempList.add(0, node.getMcuTemp());
            lineSignalPowerList.add(0, node.getSignalPower());
            lineBrightList.add(0, node.getBright());
        };
        return new LineData(imsi, timeList, lineMcuTempList, lineSignalPowerList, lineBrightList);
    }
}
