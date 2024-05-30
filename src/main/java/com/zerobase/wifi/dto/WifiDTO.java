package com.zerobase.wifi.dto;

import lombok.Data;

@Data
public class WifiDTO {
    private String distance;        // 거리
    private String mgr_no;          // 관리 번호
    private String borough;         // 자치구
    private String wifi_name;        // 와이파이명
    private String address1;        // 도로명주소
    private String address2;        // 상세주소
    private String in_floor;         // 설치위치
    private String in_type;         // 설치 유형
    private String in_by;           // 설치기관
    private String service;         // 서비스 구분
    private String network;         // 망종류
    private String c_year;          // 설치 년도
    private String in_out;          // 실내외 구분
    private String r_connection;    // 접속환경
    private double lat;             // y좌표
    private double lnt;             // x좌표
    private String Work_date;       // 작업일자
}
