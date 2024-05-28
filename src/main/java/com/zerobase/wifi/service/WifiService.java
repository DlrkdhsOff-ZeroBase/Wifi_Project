package com.zerobase.wifi.service;

import com.zerobase.wifi.dto.WifiDTO;
import com.zerobase.wifi.mapper.WifiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WifiService {

    @Autowired
    private WifiMapper wifiMapper;

    public List<WifiDTO> getList(double lat, double lnt) {
        Map<String, Double> map = new HashMap<>();
        map.put("lat", lat);
        map.put("lnt", lnt);

        List<WifiDTO> list = wifiMapper.get_Wifi_Info(map);

        for (WifiDTO dto : list) {
            dto.setDistance(getDistance(lat, lnt, dto.getLat(), dto.getLnt()));
        }
        return list;
    }

    public String getDistance(double lat1, double lng1, double lat2, double lng2) {
        int radius = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return String.format("%.4f", radius * c);
    }

}
