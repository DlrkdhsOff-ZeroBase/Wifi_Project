package com.zerobase.wifi.controller;

import com.zerobase.wifi.dto.BookMarkDTO;
import com.zerobase.wifi.dto.BookMarkGroupDTO;
import com.zerobase.wifi.dto.WifiDTO;
import com.zerobase.wifi.service.BookMarkGroupService;
import com.zerobase.wifi.service.BookMarkService;

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

    @Autowired
    private BookMarkService bookMarkService;

    @Autowired
    private BookMarkGroupService bookMarkGroupService;

    // 상세 정보
    @GetMapping("/detail")
    public String detail(@RequestParam String mgr_no, Model model) {
        return getDetailData(model, mgr_no);
    }

    // 북마크 등록
    @PostMapping("/add-bookmark")
    public String addBookmark(@RequestParam String bookMarkName, @RequestParam String mgr_no,
                              @RequestParam String wifi_name, Model model) {

        // 북마크를 선택하지 않았을 경우
        // 북마크 이름을 선택하지 않았다는 알림을 위해
        // check 변수를 model을 통해 detail.jsp로 전달
        if (Objects.equals(bookMarkName, "none")) {
            model.addAttribute("check", "add" + " false");
            return getDetailData(model, mgr_no);

        // 북마크를 선택한 경우
        // detail.jsp로 부터 전달받은 bookMarkName(북마크 이름), wifi_name(와아파이명)을 저장 후
        // 저장 성공을 했으면 success를 result에 저장 / 저장 실패 했을 경우 false을 result에 저장
        } else {
            String result = bookMarkService.addBookMark(bookMarkName, wifi_name);
            return BookMarkResult(result, "add", "detail", model);
        }
    }

    // 북마크 삭제
    @GetMapping("/delete-bookmark")
    public String deleteBookmark(@RequestParam String id, Model model) {

        // bookmark-list.jsp로부터 삭제할 북마크의 id값을 전달 받아
        // 해당 id값과 일치하는 북마크를 삭제 후
        // 삭제 성공을 했으면 success를 result에 저장 / 삭제 실패 했을 경우 false를 result에 저장
        String result = bookMarkService.deleteBookMark(id);
        return BookMarkResult(result, "delete", "/bookmark/bookmark-list", model);
    }

    // 북마크 목록
    @GetMapping("/bookmark-list")
    public String bookmarkList(Model model) {
        // 북마크를 등록한 모든 데이터를 bookMarkList에 저장 후 반환
        List<BookMarkDTO> bookMarkList = bookMarkService.getBookMark();
        model.addAttribute("bookMarkList", bookMarkList);
        return "/bookmark/bookmark-list";
    }



    //======================  공통 로직  ======================


    // 관리번호에 해당하는 dto객체를 detail.jsp파일에 전달
    private String getDetailData(Model model, String mgr_no) {

        // mgr_no(관리번호)와 일치하는 dto 객체를 wifiInfoDetail에 저장
        WifiDTO wifiInfoDetail = bookMarkService.getDetail(mgr_no);
        model.addAttribute("wifiInfoDetail", wifiInfoDetail);

        // 등록되어 있는 북마크 그룹의 이름을 bookMarkNameList에 저장
        List<BookMarkGroupDTO> bookMarkNameList = bookMarkGroupService.bookMarkNameList();
        if (bookMarkNameList != null) {
            model.addAttribute("bookMarkNameList", bookMarkNameList);
        }

        return "detail";
    }

    // 북마크 (등록, 삭제) 후 데이터를 가지고 오는 메서드
    private String BookMarkResult(String result, String operationType, String failureUrl, Model model) {
        if ("success".equals(result)) {
            List<BookMarkDTO> bookMarkList = bookMarkService.getBookMark();
            model.addAttribute("check", operationType + " success");
            model.addAttribute("bookMarkList", bookMarkList);
            return "/bookmark/bookmark-list";
        } else {
            model.addAttribute("check", operationType + " false");
            return failureUrl;
        }
    }
}
