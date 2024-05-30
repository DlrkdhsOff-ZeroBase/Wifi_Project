package com.zerobase.wifi.service;


import com.zerobase.wifi.dto.BookMarkDTO;
import com.zerobase.wifi.dto.BookMarkGroupDTO;
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


    // 북마크 그룹 추가
    public String insertBookMark(String bookMarkName, int no) {
        try {
            Map<String , Object> map = new HashMap<>();
            map.put("bookMarkName", bookMarkName);
            map.put("no", no);
            bookMarkMapper.insertBookMark(map);
            return "success";
        } catch (DataIntegrityViolationException e) {
            return "fail";
        }
    }

    // 북마크 그룹 목록
    public List<BookMarkGroupDTO> getBookMarkGroup() {
        return bookMarkMapper.getBookMarkList();
    }

    // 북마크 그룹 수정
    public String updateBookMark(long id, String bookMarkName, int no) {
        try {
            Map<String , Object> map = new HashMap<>();
            map.put("id", id);
            map.put("bookMarkName", bookMarkName);
            map.put("no", no);
            bookMarkMapper.updateBookMark(map);
            return "success";
        } catch (DataIntegrityViolationException e) {
            return "fail";
        }
    }

    // 북마크 그룹 삭제
    public String deleteBookMarkGroup(String id) {
        bookMarkMapper.deleteBookMarkGroup(Long.parseLong(id));
        try {
            bookMarkMapper.deleteBookMarkGroup(Long.parseLong(id));
            return "success";
        } catch (DataIntegrityViolationException e) {
            return "fail";
        }
    }

    // 북마크 등록
    public String addBookMark(String bookMarkName, int no, String wifi_name) {

        try {
            Map<String , Object> map = new HashMap<>();
            map.put("bookMarkName", bookMarkName);
            map.put("no", no);
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

    public WifiDTO getDetail(String mgr_no) {
        return bookMarkMapper.getDetail(mgr_no);
    }

    public int getNo(String bookMarkName) {
        return bookMarkMapper.getNo(bookMarkName);
    }
}
