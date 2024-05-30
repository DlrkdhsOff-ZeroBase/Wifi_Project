package com.zerobase.wifi.service;

import com.zerobase.wifi.dto.WifiDTO;
import com.zerobase.wifi.mapper.HistoryMapper;
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

    @Autowired
    private HistoryMapper historyMapper;

    // 와이파이 정보를 가지고 오는 메서드
    public List<WifiDTO> getWifiInfoList(double lat, double lnt) {
        Map<String, Double> map = new HashMap<>();
        map.put("lat", lat);
        map.put("lnt", lnt);

        // 검색한 좌표를 history에 저장
        historyMapper.saveHistory(map);

        List<WifiDTO> wifiInfoList = wifiMapper.getWifiInfoList(map);
        for (WifiDTO dto : wifiInfoList) {
            dto.setDistance(getDistance(lat, lnt, dto.getLat(), dto.getLnt()));
        }
        return wifiInfoList;
    }

    // 현재 좌표와 거리 비교할 좌표의 거리 구하는 메서드
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
