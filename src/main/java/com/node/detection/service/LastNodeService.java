package com.node.detection.service;

import com.node.detection.entity.mongo.LastNode;
import com.node.detection.entity.util.MyPageRequest;
import com.node.detection.entity.util.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface LastNodeService {

    PageResult<LastNode> getLastNode(int page, int size);

    List<LastNode> getAllLastNode();

    LastNode deleteIMSIFromLastNodeList(String imsi);

    LastNode saveLastNode(LastNode lastNode);
}
