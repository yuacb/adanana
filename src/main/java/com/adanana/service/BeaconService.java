package com.adanana.service;

import com.adanana.model.Beacon;

import java.util.List;

public interface BeaconService {
    void insert(Beacon beacon);
    void insertBatch(List<String> beaconList);
    List<String> find(Beacon beacon);
}
