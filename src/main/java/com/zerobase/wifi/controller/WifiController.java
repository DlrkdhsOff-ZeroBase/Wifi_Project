package com.zerobase.wifi.controller;

import java.util.*;
import com.zerobase.wifi.dto.WifiDTO;
import com.zerobase.wifi.service.WifiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WifiController {

    @Autowired
    private WifiService wifiService;

    @GetMapping("/wifi-Info")
    public String wifiInfo(@RequestParam("lat") double lat,
                           @RequestParam("lnt") double lnt,
                           Model model) {

        List<WifiDTO> list = wifiService.getList(lat, lnt);
        for(WifiDTO wifi : list) {
            System.out.println(wifi.getDistance());
        }

        model.addAttribute("list", list);
        return "home";
    }
}
