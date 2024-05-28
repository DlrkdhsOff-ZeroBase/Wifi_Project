package com.zerobase.wifi.controller;

import java.util.*;
import com.zerobase.wifi.dto.WifiDTO;
import com.zerobase.wifi.service.WifiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WifiController {

    @Autowired
    private WifiService wifiService;

    @GetMapping("/wifi-Info")
    public String wifiInfo(Model model) {
        List<WifiDTO> list = wifiService.getList();
        for(WifiDTO wifi : list) {
            System.out.println(wifi.getMgr_no());
        };
        model.addAttribute("list", list);
        return "home";
    }
}
