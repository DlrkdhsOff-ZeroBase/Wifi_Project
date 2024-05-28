package com.zerobase.wifi.service;


import com.zerobase.wifi.dto.BookMarkDTO;
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


    // 북마크 추가
    public String insertBookMark(String name, int no) {
        try {
            Map<String , Object> map = new HashMap<>();
            map.put("name", name);
            map.put("no", no);
            bookMarkMapper.insertBookMark(map);
            return "success";
        } catch (DataIntegrityViolationException e) {
            return "fail";
        }
    }

    // 북마크 목록
    public List<BookMarkDTO> getBookMark() {
        return bookMarkMapper.getBookMarkList();
    }

    // 북마크 수정
    public String updateBookMark(long id, String name, int no) {
        try {
            Map<String , Object> map = new HashMap<>();
            map.put("id", id);
            map.put("name", name);
            map.put("no", no);
            bookMarkMapper.updateBookMark(map);
            return "success";
        } catch (DataIntegrityViolationException e) {
            return "fail";
        }
    }

    // 북마크 삭제
    public String deleteBookMark(String id) {
        bookMarkMapper.deleteBookMark(Long.parseLong(id));
        try {
            bookMarkMapper.deleteBookMark(Long.parseLong(id));
            return "success";
        } catch (DataIntegrityViolationException e) {
            return "fail";
        }
    }
}
