package com.zerobase.wifi.mapper;

import com.zerobase.wifi.dto.WifiDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WifiMapper {

    List<WifiDTO> get_Wifi_Info(Map<String, Double> map);
}
