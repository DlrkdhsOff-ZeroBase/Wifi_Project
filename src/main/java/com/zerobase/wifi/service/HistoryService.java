package com.zerobase.wifi.service;

import com.zerobase.wifi.dto.HistoryDTO;
import com.zerobase.wifi.mapper.HistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    // 검색 기록을 가지고 오는 메서드
    public List<HistoryDTO> getHistoryList() {
        return historyMapper.getHistoryList();
    }

    // 검색 기록울 삭제하는 메서드
    public List<HistoryDTO> deleteHistory(long id) {
        historyMapper.deleteHistory(id);
        return historyMapper.getHistoryList();
    }
}
