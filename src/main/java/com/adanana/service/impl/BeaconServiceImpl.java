package com.adanana.service.impl;

import com.adanana.dao.BeaconDao;
import com.adanana.model.Beacon;
import com.adanana.service.BeaconService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "beaconServiceImpl")
public class BeaconServiceImpl implements BeaconService {
    @Resource
    private BeaconDao beaconDao;
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
