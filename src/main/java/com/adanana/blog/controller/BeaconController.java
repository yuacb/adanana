package com.adanana.blog.controller;

import com.adanana.blog.core.ResponseObject;
import com.adanana.blog.model.Beacon;
import com.adanana.blog.service.BeaconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
@Controller
public class BeaconController extends  BaseController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BeaconService beaconService;

    /**
     * 每次保存 更新
     * @param beacons
     * @return
     */
    @RequestMapping(value ="/beacon/statistics", method = RequestMethod.POST)
    public Object statistics(String  beacons){
        Beacon beaconParam = new Beacon();
        beaconParam.setType("1");
        //查出已经配好的
        List<Beacon> beaconsList = beaconService.find(beaconParam);
        Map<String,String> map = new HashMap<>();
        //已经配置数量
        map.put("count",""+beaconsList.size());

        return ResponseObject.success(map);
    }

    /**
     * 插入
     * @param beacons
     * @return
     */
    @RequestMapping(value ="/beacon/insert", method = RequestMethod.POST)
    @ResponseBody
    public Object insert(String  beacons){
        String[] beaconArry = beacons.split("\\s");
        Beacon beacon =null;
        List beaconList = new ArrayList<Beacon>();
        for ( String item:beaconArry ) {
            beacon = new Beacon();
            item.replaceAll("\\s","");
            if(item.trim()==""|| item.length()<5)
                continue;
            beacon.setMinor(item.trim());
            //前期数据已经导入 1 代表自己配的
            beacon.setType("1");
            beacon.setCreateTime(new Date());
            beaconList.add(beacon);
        }
        //保存的时候取 差集(去重复)
        //去除所有的
        List<Beacon> beaconsAll = beaconService.find();
        beaconList.removeAll(beaconsAll);
        if(beaconList.size() != 0){
            beaconService.insertBatch(beaconList);
        }
        return  ResponseObject.success();
    }
}
