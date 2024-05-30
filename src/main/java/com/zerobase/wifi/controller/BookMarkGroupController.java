package com.zerobase.wifi.controller;

import com.zerobase.wifi.dto.BookMarkGroupDTO;
import com.zerobase.wifi.service.BookMarkGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookMarkGroupController {

    @Autowired
    private BookMarkGroupService bookMarkGroupService;


    // 북마크 그룹 정보 가지고 오기
    @GetMapping("/bookmark-group")
    public String bookmarkGroup(Model model) {

        // 등록 되어 있는 북마크 그룹을 bookMarkGroupList에 저장
        List<BookMarkGroupDTO> bookMarkGroupList = bookMarkGroupService.getBookMarkGroupList();

        // bookMarkGroupList를 모델을 통해 bookmark-group.jsp로 전달
        model.addAttribute("bookMarkGroupList", bookMarkGroupList);
        return "/bookmark/bookmark-group";
    }


    // 북마크 그룹을 추가는 add-bookmark-group.jsp로 이동
    @GetMapping("/add-bookmark-group")
    public String addBookmarkGroup() {
        return "/bookmark/add-bookmark-group";
    }


    // 북마크 그룹 추가
    @PostMapping("/add-bookmark-group")
    public String saveBookmarkGroup(@RequestParam String bookMarkName, @RequestParam String no, Model model) {

        // add-bookmark-group로 부터 전달 받은 bookMarkName(북마크 이름), 순서(no)를 저장 후
        // 저장 성공을 했으면 success를 result에 저장 / 저장 실패 했을 경우 false을 result에 저장
        String result = bookMarkGroupService.insertBookMarkGroup(bookMarkName, Integer.parseInt(no));
        return BookMarkGroupResult(result, "insert", model,  "/bookmark/add-bookmark-group");
    }


    // 북마크 그룹 수정 jsp 이동
    @GetMapping("/update-bookmark-group")
    public String updateBookmarkGroupForm(@RequestParam String id, Model model) {

        // 등록한 북마크 그룹 수정을 위해 수정할 북마크 그룹의 id값을 add-bookmark-group.jsp로 전달
        model.addAttribute("id", id);
        return "/bookmark/add-bookmark-group";
    }

    // 북마크 그룹 수정
    @PostMapping("/update-bookmark-group")
    public String updateBookmarkGroup(@RequestParam String id,
                                      @RequestParam String bookMarkName,
                                      @RequestParam String no, Model model) {

        // bookmark-group.jsp로부터 id(수정할 북마크 그룹의 id),
        // bookMarkName(수정할 북마크 이름), no(수정할 순서)를 전달 받아
        // id값과 일치하는 객체를 수정 후
        // 수정 성공을 했으면 success를 result에 저장 / 수정 실패 했을 경우 false를 result에 저장
        String result = bookMarkGroupService.updateBookMark(Long.parseLong(id), bookMarkName, Integer.parseInt(no));
        return BookMarkGroupResult(result, "update", model, "/bookmark/add-bookmark-group");
    }

    // 북마크 그룹 삭제
    @GetMapping("/delete-bookmark-group")
    public String deleteBookmarkGroup(@RequestParam String id, Model model) {

        // bookmark-group.jsp로부터 삭제할 북마크의 id값을 전달 받아
        // 해당 id값과 일치하는 북마크를 삭제 후
        // 삭제 성공을 했으면 success를 result에 저장 / 삭제 실패 했을 경우 false를 result에 저장
        String result = bookMarkGroupService.deleteBookMarkGroup(id);
        return BookMarkGroupResult(result, "delete", model,  "/bookmark/bookmark-group");
    }


    //======================  공통 로직  ======================


    // 북마크 그룹 (추가/수정/삭제) 후 데이터를 가지고 오는 메서드
    private String BookMarkGroupResult(String result, String sqlType, Model model, String failureUrl) {

        // (추가/수정/삭제)가 성공했을 경우
        // (추가/수정/삭제)가 성공했다는 알림을 위해 check변수와
        // 북마크 그룹의 정보를 담은 bookMarkGroupList를 model을 통해 bookmark-group.jsp로 전달
        if ("success".equals(result)) {
            List<BookMarkGroupDTO> bookMarkGroupList = bookMarkGroupService.getBookMarkGroupList();
            model.addAttribute("check", sqlType + " success");
            model.addAttribute("bookMarkGroupList", bookMarkGroupList);
            return "/bookmark/bookmark-group";

        // (추가/수정/삭제)가 실패 했을 경우
        // 현재 메서드를 호출한 메서드에서 매개값을로 넘겨준 jsp파일에
        // (추가/수정/삭제)를 실패 했다는 알림을 위해 check 변수를
        // model을 통해 전달
        // 만약 update일 경우 id값을 초기화
        } else {
            model.addAttribute("check", sqlType + " false");
            model.addAttribute("id", "");
            return failureUrl;
        }
    }
}
