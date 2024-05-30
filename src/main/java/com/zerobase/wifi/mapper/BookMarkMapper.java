package com.zerobase.wifi.mapper;

import com.zerobase.wifi.dto.BookMarkDTO;
import com.zerobase.wifi.dto.WifiDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMarkMapper {


    void addBookMark(Map<String, Object> map);

    List<BookMarkDTO> getBookMark();

    void deleteBookMark(long id);

    WifiDTO getDetail(String mgr_no);

}
