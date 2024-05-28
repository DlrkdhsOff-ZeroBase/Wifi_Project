package com.zerobase.wifi.controller;

import com.zerobase.wifi.dto.BookMarkDTO;
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

//    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private BookMarkService bookMarkService;

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

    // 북마크 추가 jsp 이동
    @GetMapping("/add-bookmark-group")
    public String addBookmarkGroup() {
        return "/bookmark/add-bookmark-group";
    }

    // 북마크 추가
    @PostMapping("/add-bookmark-group")
    public String saveBookmarkGroup(@RequestParam String name, @RequestParam String no, Model model) {
        String result = bookMarkService.insertBookMark(name, Integer.parseInt(no));
        return processBookmarkResult(result, "insert", model,  "/bookmark/add-bookmark-group");
    }

    // 북마크 수정 jsp 이동
    @GetMapping("/update-bookmark-group")
    public String updateBookmarkGroupForm(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return "/bookmark/add-bookmark-group";
    }

    // 북마크 수정
    @PostMapping("/update-bookmark-group")
    public String updateBookmarkGroup(@RequestParam String id, @RequestParam String name, @RequestParam String no, Model model) {
        String result = bookMarkService.updateBookMark(Long.parseLong(id), name, Integer.parseInt(no));
        return processBookmarkResult(result, "update", model, "/bookmark/add-bookmark-group");
    }

    // 북마크 삭제
    @GetMapping("/delete-bookmark-group")
    public String deleteBookmarkGroup(@RequestParam String id, Model model) {
        String result = bookMarkService.deleteBookMark(id);
        return processBookmarkResult(result, "delete", model,  "/bookmark/bookmark-group");
    }

    // 공통적으로 추가/수정/삭제 후 데이터를 가지고 오는 메서드
    private String processBookmarkResult(String result, String operationType, Model model, String failureUrl) {
        if ("success".equals(result)) {
            List<BookMarkDTO> list = bookMarkService.getBookMark();
            model.addAttribute("check", operationType + " success");
            model.addAttribute("list", list);
            return "/bookmark/bookmark-group";
        } else {
            model.addAttribute("check", operationType + " false");
            return failureUrl;
        }
    }
}