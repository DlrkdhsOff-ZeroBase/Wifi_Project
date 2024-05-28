package com.zerobase.wifi.service;

import com.zerobase.wifi.mapper.BookMarkMapper;
import com.zerobase.wifi.mapper.WifiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookMarkService {

    @Autowired
    private BookMarkMapper bookMarkMapper;


}
