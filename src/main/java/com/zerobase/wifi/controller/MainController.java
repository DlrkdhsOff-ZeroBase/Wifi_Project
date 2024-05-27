package com.zerobase.wifi.controller;

import com.zerobase.wifi.dto.UserNameDTO;
import com.zerobase.wifi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/load-wifi")
    public String loadWifi() {
        List<UserNameDTO> userList = userService.getUserList();
        for (UserNameDTO user : userList) {
            System.out.println(user);
        }
        return null;
    }
}