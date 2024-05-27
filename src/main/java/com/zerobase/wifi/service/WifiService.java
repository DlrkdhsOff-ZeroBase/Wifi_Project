package com.zerobase.wifi.service;

import com.zerobase.wifi.dto.WifiDTO;
import com.zerobase.wifi.mapper.WifiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class WifiService {

    @Autowired
    private WifiMapper wifiMapper;

    @GetMapping
    public List<WifiDTO> getList() {
        return wifiMapper.get_Wifi_Info();
    }
}
