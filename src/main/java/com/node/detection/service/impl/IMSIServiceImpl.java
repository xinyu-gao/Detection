package com.node.detection.service.impl;

import com.node.detection.dao.IMSIRepository;
import com.node.detection.entity.mongo.IMSIInfo;
import com.node.detection.service.IMSIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IMSIServiceImpl implements IMSIService {

    @Autowired
    private IMSIRepository imsiRepository;



    @Override
    public boolean insertIMSI(IMSIInfo imsiInfo) {
        return null != imsiRepository.save(imsiInfo);
    }

    @Override
    public boolean deleteIMSI(String imsi) {
        return null != imsiRepository.deleteIMSIInfoByImsi(imsi);
    }

    @Override
    public List<IMSIInfo> getIMSIlist() {
        return imsiRepository.findAll();
    }
}
