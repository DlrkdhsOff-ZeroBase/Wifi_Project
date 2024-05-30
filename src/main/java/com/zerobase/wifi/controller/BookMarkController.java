package com.zerobase.wifi.controller;

import com.zerobase.wifi.dto.BookMarkDTO;
import com.zerobase.wifi.dto.BookMarkGroupDTO;
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
import java.util.Objects;

@Controller
public class BookMarkController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private BookMarkService bookMarkService;


    @GetMapping("/detail")
    public String detail(HttpServletRequest request, Model model) {
        String mgr_no = request.getParameter("mgr_no");
        return getDetailData(request, model, mgr_no);
    }

    // 북마크 그룹 정보 가지고 오기
    @GetMapping("/bookmark-group")
    public String bookmarkGroup(Model model) {
        List<BookMarkGroupDTO> list = bookMarkService.getBookMarkGroup();
        model.addAttribute("list", list);
        return "/bookmark/bookmark-group";
    }

    // 북마크 그룹 추가 jsp 이동
    @GetMapping("/add-bookmark-group")
    public String addBookmarkGroup() {
        return "/bookmark/add-bookmark-group";
    }

    // 북마크 그룹 추가
    @PostMapping("/add-bookmark-group")
    public String saveBookmarkGroup(@RequestParam String bookMarkName, @RequestParam String no, Model model) {
        log.info("bookMarkName : {}", bookMarkName);
        String result = bookMarkService.insertBookMark(bookMarkName, Integer.parseInt(no));
        return BookMarkGroupResult(result, "insert", model,  "/bookmark/add-bookmark-group");
    }

    // 북마크 그룹 수정 jsp 이동
    @GetMapping("/update-bookmark-group")
    public String updateBookmarkGroupForm(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return "/bookmark/add-bookmark-group";
    }

    // 북마크 그룹 수정
    @PostMapping("/update-bookmark-group")
    public String updateBookmarkGroup(@RequestParam String id, @RequestParam String bookMarkName, @RequestParam String no, Model model) {
        String result = bookMarkService.updateBookMark(Long.parseLong(id), bookMarkName, Integer.parseInt(no));
        return BookMarkGroupResult(result, "update", model, "/bookmark/add-bookmark-group");
    }

    // 북마크 그룹 삭제
    @GetMapping("/delete-bookmark-group")
    public String deleteBookmarkGroup(@RequestParam String id, Model model) {
        String result = bookMarkService.deleteBookMarkGroup(id);
        return BookMarkGroupResult(result, "delete", model,  "/bookmark/bookmark-group");
    }


    // 북마크 등록
    @PostMapping("/add-bookmark")
    public String addBookmark(@RequestParam String bookMarkName, @RequestParam String mgr_no,
                              @RequestParam String wifi_name, HttpServletRequest request, Model model) {

        if (Objects.equals(bookMarkName, "none")) {
            model.addAttribute("check", "add" + " false");
            return getDetailData(request, model, mgr_no);
        } else {
            int no = bookMarkService.getNo(bookMarkName);
            String result = bookMarkService.addBookMark(bookMarkName, no, wifi_name);
            return BookMarkResult(result, "add", "detail", model);
        }
    }

    // 북마크 삭제
    @GetMapping("/delete-bookmark")
    public String deleteBookmark(@RequestParam String id, Model model) {
        String result = bookMarkService.deleteBookMark(id);
        return BookMarkResult(result, "delete", "/bookmark/bookmark-list", model);
    }

    // 북마크 목록
    @GetMapping("/bookmark-list")
    public String bookmarkList(Model model) {
        List<BookMarkDTO> list = bookMarkService.getBookMark();
        model.addAttribute("list", list);
        return "/bookmark/bookmark-list";
    }

    //======================  공통 로직  ======================

    // 북마크 그룹 (추가/수정/삭제) 후 데이터를 가지고 오는 메서드
    private String BookMarkGroupResult(String result, String operationType, Model model, String failureUrl) {
        if ("success".equals(result)) {
            List<BookMarkGroupDTO> list = bookMarkService.getBookMarkGroup();
            model.addAttribute("check", operationType + " success");
            model.addAttribute("list", list);
            return "/bookmark/bookmark-group";
        } else {
            model.addAttribute("check", operationType + " false");
            model.addAttribute("id", "");
            return failureUrl;
        }
    }

    // 북마크 (등록, 삭제) 후 데이터를 가지고 오는 메서드
    private String BookMarkResult(String result, String operationType, String failureUrl, Model model) {
        if ("success".equals(result)) {
            List<BookMarkDTO> list = bookMarkService.getBookMark();
            model.addAttribute("check", operationType + " success");
            model.addAttribute("list", list);
            return "/bookmark/bookmark-list";
        } else {
            model.addAttribute("check", operationType + " false");
            return failureUrl;
        }
    }

    // 관리번호에 해당하는 dto객체를 detail.jsp파일에 전달
    private String getDetailData(HttpServletRequest request, Model model, String mgr_no) {
        HttpSession session = request.getSession();
        List<WifiDTO> wifiList = (List<WifiDTO>) session.getAttribute("wifiList");

        if (wifiList != null) {
            for (WifiDTO wifi : wifiList) {
                if (wifi.getMgr_no().equals(mgr_no)) {
                    model.addAttribute("wifi", wifi);
                    break;
                }
            }
        }

        List<BookMarkGroupDTO> list = bookMarkService.getBookMarkGroup();
        if (list != null) {
            model.addAttribute("list", list);
        }

        return "detail";
    }
}
