package com.zerobase.wifi.service;


import com.zerobase.wifi.dto.BookMarkDTO;
import com.zerobase.wifi.dto.WifiDTO;
import com.zerobase.wifi.mapper.BookMarkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookMarkService {

    @Autowired
    private BookMarkMapper bookMarkMapper;

    // mgr_no(관리번호)와 일치하는 wifiDTO 객체를 반환
    public WifiDTO getDetail(String mgr_no) {
        return bookMarkMapper.getDetail(mgr_no);
    }

    // 북마크 등록
    public String addBookMark(String bookMarkName, String wifi_name) {

        try {
            Map<String , Object> map = new HashMap<>();
            map.put("bookMarkName", bookMarkName);
            map.put("wifi_name", wifi_name);
            bookMarkMapper.addBookMark(map);
            return "success";
        } catch (DataIntegrityViolationException e) {
            return "fail";
        }
    }

    // 등록한 북마크 목록
    public List<BookMarkDTO> getBookMark() {
        return bookMarkMapper.getBookMark();
    }


    // 등록한 북마크 삭제
    public String deleteBookMark(String id) {
        try {
            bookMarkMapper.deleteBookMark(Long.parseLong(id));
            return "success";
        } catch (DataIntegrityViolationException e) {
            return "fail";
        }
    }

}
