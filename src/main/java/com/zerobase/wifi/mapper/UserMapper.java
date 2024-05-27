package com.zerobase.wifi.mapper;

import com.zerobase.wifi.dto.UserNameDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserNameDTO> getUserList();
}
