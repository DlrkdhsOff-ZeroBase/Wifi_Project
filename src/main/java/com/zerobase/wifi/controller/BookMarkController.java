package com.zerobase.wifi.controller;

import com.zerobase.wifi.dto.BookMarkDTO;
import com.zerobase.wifi.dto.WifiDTO;
import com.zerobase.wifi.service.BookMarkService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookMarkController {

    private Logger log = LoggerFactory.getLogger(getClass());

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

    // 북마크 정보 가지고 오기
    @GetMapping("/bookmark-group")
    public String bookmarkGroup(Model model) {
        List<BookMarkDTO> list = bookMarkService.getBookMark();
        model.addAttribute("list", list);
        return "/bookmark/bookmark-group";
    }

    // 북마크 정보 추가 jsp로 이동
    @GetMapping("add-bookmark-group")
    public String addBookmarkGroup() {
        return "/bookmark/add-bookmark-group";
    }

    // 북마크 추가 성공 : bookmark-group로 이동
    // 북마크 추가 실패 : add-bookmark-group로 이동
    @PostMapping("add-bookmark-group")
    public String SaveBookmarkGroup(@RequestParam String name, @RequestParam String no, Model model) {
        // 매개변수 값이 비어 있는지 확인
        if(name == null || no == null || name.isEmpty() || no.isEmpty()) {
            model.addAttribute("check", "empty");
            return "/bookmark/add-bookmark-group";
        }
        List<BookMarkDTO> list = bookMarkService.insertBookMark(name, Integer.parseInt(no));

        // 중복된 순서인지 확인
        if (list != null) {
            model.addAttribute("check", "true");
            model.addAttribute("list", list);
            return "/bookmark/bookmark-group";
        } else {
            model.addAttribute("check", "no");
            return "/bookmark/add-bookmark-group";
        }
    }
}
