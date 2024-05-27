package com.zerobase.wifi.mapper;

import com.zerobase.wifi.dto.WifiDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WifiMapper {
    void save(WifiDTO wifiDTO);
}