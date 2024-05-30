package com.zerobase.wifi.mapper;

import com.zerobase.wifi.dto.BookMarkGroupDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMarkGroupMapper {
    List<BookMarkGroupDTO> bookMarkNameList();

    void insertBookMarkGroup(Map<String, Object> map);

    List<BookMarkGroupDTO> getBookMarkGroupList();

    void updateBookMarkGroup(Map<String, Object> map);

    void deleteBookMarkGroup(long id);

}
