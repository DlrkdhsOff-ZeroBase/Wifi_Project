package com.zerobase.wifi.mapper;

import com.zerobase.wifi.dto.HistoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface HistoryMapper {
    // 검색기록 저장
    void saveHistory(Map<String, Double> map);

    // 검색기록 반환
    List<HistoryDTO> getHistoryList();

    // 검색기록 삭제
    void deleteHistory(long id);

}
