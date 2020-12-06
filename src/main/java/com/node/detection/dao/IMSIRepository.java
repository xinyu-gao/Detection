package com.node.detection.dao;

import com.node.detection.entity.mongo.IMSIInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMSIRepository extends MongoRepository<IMSIInfo, String> {

    IMSIInfo save(IMSIInfo imsiInfo);

    IMSIInfo deleteIMSIInfoByImsi(String imsi);

    List<IMSIInfo> findAll();
}
