package com.zerobase.wifi.dto;

import lombok.Data;

@Data
public class HistoryDTO {
    private long id;
    private double lat;
    private double lnt;
    private String date;
}
