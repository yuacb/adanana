package com.adanana.blog.service.impl;

import com.adanana.blog.dao.BeaconMapper;
import com.adanana.blog.model.Beacon;
import com.adanana.blog.service.BeaconService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "beaconServiceImpl")
public class BeaconServiceImpl implements BeaconService {
    @Resource
    private BeaconMapper beaconDao;
    @Override
    public void insert(Beacon beacon) {
        beaconDao.insert(beacon);
    }

    @Override
    public void insertBatch(List<String> beaconList) {
        beaconDao.insertBatch(beaconList);
    }

    @Override
    public List<Beacon> find(Beacon beacon) {
       return beaconDao.find(beacon);
    }

    @Override
    public List<Beacon> find() {
        Beacon beacon = new Beacon();
        return beaconDao.find(beacon);
    }
}
