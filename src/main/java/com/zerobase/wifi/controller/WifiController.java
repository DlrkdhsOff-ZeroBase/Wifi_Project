package com.zerobase.wifi.controller;

import java.util.*;

import com.zerobase.wifi.dto.WifiDTO;
import com.zerobase.wifi.service.WifiService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WifiController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private WifiService wifiService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/home")
    public String test() {
        return "index";
    }


    // 와아피이 기록 출력
    @PostMapping("/wifi-Info")
    public String wifiInfo(@RequestParam double lat,
                           @RequestParam double lnt,
                           Model model) {

        log.info("lat = {}, lnt = {}", lat, lnt);

        List<WifiDTO> wifiInfoList = wifiService.getList(lat, lnt);
        model.addAttribute("wifiInfoList", wifiInfoList);
        return "index";
    }
}
