package com.zerobase.wifi.service;

import com.zerobase.wifi.dto.UserNameDTO;
import com.zerobase.wifi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<UserNameDTO> getUserList() {
        return userMapper.getUserList();
    }
}
