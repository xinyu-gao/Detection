package com.node.detection.service.impl;

import com.node.detection.dao.LastNodeRepository;
import com.node.detection.entity.mongo.LastNode;
import com.node.detection.entity.util.MyPageRequest;
import com.node.detection.entity.util.PageResult;
import com.node.detection.service.LastNodeService;
import com.node.detection.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LastNodeServiceImpl implements LastNodeService {

    @Autowired
    private LastNodeRepository lastNodeRepository;

    @Override
    public PageResult<LastNode> getLastNode(int page, int size) {
        Page<LastNode> nodeList = lastNodeRepository.findAll(PageRequest.of(page, size));
        log.info(nodeList.toString());
        return PageUtil.setResult(nodeList, page);
    }

    @Override
    public List<LastNode> getAllLastNode() {
        return lastNodeRepository.findAll();
    }
    @Override
    public LastNode deleteIMSIFromLastNodeList(String imsi) {
        return lastNodeRepository.deleteLastNodeByIMSI(imsi);
    }

    @Override
    public LastNode saveLastNode(LastNode lastNode) {
        return lastNodeRepository.save(lastNode);
    }
}
