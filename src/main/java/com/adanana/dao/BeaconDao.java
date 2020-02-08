package com.adanana.dao;

import com.adanana.model.Beacon;
import java.util.List;

public interface BeaconDao {
     void insert(Beacon beacon);
     void insertBatch(List<String> beaconList);
     List<Beacon> find(Beacon beacon);

}
