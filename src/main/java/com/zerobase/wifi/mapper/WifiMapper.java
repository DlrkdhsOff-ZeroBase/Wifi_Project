package com.zerobase.wifi.mapper;

import com.zerobase.wifi.dto.WifiDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WifiMapper {

    // 와이파이 정보 저장
    void save(WifiDTO wifiDTO);

    // 와이파이 정보 가지고 오기
    List<WifiDTO> get_Wifi_Info(Map<String, Double> map);

    // history 목록 저장
    void saveHistory(Map<String, Double> map);
}
