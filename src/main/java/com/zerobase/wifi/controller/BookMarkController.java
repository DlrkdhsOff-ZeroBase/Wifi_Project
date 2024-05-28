package com.zerobase.wifi.controller;

import com.zerobase.wifi.dto.WifiDTO;
import com.zerobase.wifi.service.BookMarkService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookMarkController {

    @Autowired
    private BookMarkService bookMarkService;

    // 상세정보
    @GetMapping("/detail")
    public String detail(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        List<WifiDTO> wifiList = (List<WifiDTO>) session.getAttribute("wifiList");

        String mgr_no = request.getParameter("mgr_no");
        if (wifiList != null) {
            for (WifiDTO wifi : wifiList) {
                if (wifi.getMgr_no().equals(mgr_no)) {
                    model.addAttribute("wifi", wifi);
                    break;
                }
            }
        }
        return "detail";
    }

    @GetMapping("/bookmark-group")
    public String bookmarkGroup() {
        return "/bookmark/bookmark-group";
    }

    @GetMapping("add-bookmark-group")
    public String addBookmarkGroup() {
        return "/bookmark/add-bookmark-group";
    }

    @PostMapping("add-bookmark-group")
    public String SaveBookmarkGroup(@RequestParam String name, @RequestParam int no, Model model) {

        return "/bookmark/bookmark-group";
    }
}
