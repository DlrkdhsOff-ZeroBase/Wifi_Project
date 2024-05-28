package com.zerobase.wifi.mapper;

import com.zerobase.wifi.dto.BookMarkDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMarkMapper {

    void insertBookMark(Map<String, Object> map);

    List<BookMarkDTO> getBookMarkList();
}
