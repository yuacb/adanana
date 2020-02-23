package com.adanana.blog.service;

import com.adanana.blog.model.Beacon;

import java.util.List;

public interface BeaconService {
    void insert(Beacon beacon);
    void insertBatch(List<String> beaconList);
    List<Beacon> find(Beacon beacon);
    List<Beacon> find();
}
