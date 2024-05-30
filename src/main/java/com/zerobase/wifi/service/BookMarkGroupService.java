package com.zerobase.wifi.service;

import com.zerobase.wifi.dto.BookMarkGroupDTO;
import com.zerobase.wifi.mapper.BookMarkGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookMarkGroupService {

    @Autowired
    private BookMarkGroupMapper bookMarkGroupMapper;

    // 등록되어 있는 북마크 그룹의 북마크 이름을 반환
    public List<BookMarkGroupDTO> bookMarkNameList() {
        return bookMarkGroupMapper.bookMarkNameList();
    }

    // 북마크 그룹 추가
    public String insertBookMarkGroup(String bookMarkName, int no) {
        try {
            Map<String , Object> map = new HashMap<>();
            map.put("bookMarkName", bookMarkName);
            map.put("no", no);
            bookMarkGroupMapper.insertBookMarkGroup(map);
            return "success";
        } catch (DataIntegrityViolationException e) {
            return "false";
        }
    }

    // 북마크 그룹 목록
    public List<BookMarkGroupDTO> getBookMarkGroupList() {
        return bookMarkGroupMapper.getBookMarkGroupList();
    }

    // 북마크 그룹 수정
    public String updateBookMark(long id, String bookMarkName, int no) {
        try {
            Map<String , Object> map = new HashMap<>();
            map.put("id", id);
            map.put("bookMarkName", bookMarkName);
            map.put("no", no);
            bookMarkGroupMapper.updateBookMarkGroup(map);
            return "success";
        } catch (DataIntegrityViolationException e) {
            return "false";
        }
    }

    // 북마크 그룹 삭제
    public String deleteBookMarkGroup(String id) {
        try {
            bookMarkGroupMapper.deleteBookMarkGroup(Long.parseLong(id));
            return "success";
        } catch (DataIntegrityViolationException e) {
            return "false";
        }
    }
}
