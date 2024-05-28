package com.zerobase.wifi.service;


import com.zerobase.wifi.dto.BookMarkDTO;
import com.zerobase.wifi.mapper.BookMarkMapper;
import com.zerobase.wifi.mapper.WifiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookMarkService {

    @Autowired
    private BookMarkMapper bookMarkMapper;


    public List<BookMarkDTO> insertBookMark(String name, int no) {
        try {
            Map<String , Object> map = new HashMap<>();
            map.put("name", name);
            map.put("no", no);
            bookMarkMapper.insertBookMark(map);
            return getBookMark();
        } catch (DataIntegrityViolationException e) {
            // 중복된 순서라면 null을 반환
            return null;
        }
    }

    public List<BookMarkDTO> getBookMark() {
        return bookMarkMapper.getBookMarkList();
    }
}
