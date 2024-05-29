package com.zerobase.wifi.mapper;

import com.zerobase.wifi.dto.BookMarkDTO;
import com.zerobase.wifi.dto.BookMarkGroupDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMarkMapper {

    void insertBookMark(Map<String, Object> map);

    List<BookMarkGroupDTO> getBookMarkList();

    void updateBookMark(Map<String, Object> map);

    void deleteBookMarkGroup(long id);


    void addBookMark(Map<String, Object> map);

    List<BookMarkDTO> getBookMark();

    void deleteBookMark(long id);
}
