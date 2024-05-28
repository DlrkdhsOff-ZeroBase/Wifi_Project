package com.zerobase.wifi.dto;

import lombok.Data;

@Data
public class HistoryDTO {
    private long id;        // 아이디
    private double lat;     // x 좌표
    private double lnt;     // y 좌표
    private String date;    // 검색 시간
}
