package com.zerobase.wifi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WifiController {

    @GetMapping("/wifi-Info")
    public String wifiInfo() {
        return "wifi-info";
    }
}
