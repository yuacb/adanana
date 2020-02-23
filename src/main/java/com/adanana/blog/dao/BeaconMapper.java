package com.adanana.blog.dao;

import com.adanana.blog.model.Beacon;
import java.util.List;

public interface BeaconMapper {
     void insert(Beacon beacon);
     void insertBatch(List<String> beaconList);
     List<Beacon> find(Beacon beacon);

}
