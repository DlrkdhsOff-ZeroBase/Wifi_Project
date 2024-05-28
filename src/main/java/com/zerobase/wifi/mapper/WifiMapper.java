package com.zerobase.wifi.mapper;

import com.zerobase.wifi.dto.HistoryDTO;
import com.zerobase.wifi.dto.WifiDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface WifiMapper {

    // 와이파이 정보 저장
    void save(WifiDTO wifiDTO);

    // 현재 내 위치에서 거리순으로 데이터 반환
    List<WifiDTO> get_Wifi_Info(Map<String, Double> map);

    // 검색기록 저장
    void saveHistory(Map<String, Double> map);

    // 검색 기록 반환
    List<HistoryDTO> getHistory();

    // 프로시저를 통해 검색기록 삭제
    void deleteHistory(long id);

    // 프로시저를 통한 검색기록 저장
    void insertHistory(Map<String, Double> map);
}
