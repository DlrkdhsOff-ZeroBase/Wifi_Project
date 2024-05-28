package com.zerobase.wifi.controller;

import com.zerobase.wifi.dto.HistoryDTO;
import com.zerobase.wifi.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    // 기록 출력
    @GetMapping("/getHistory")
    public String history(Model model) {

        List<HistoryDTO> list = historyService.getHistory();
        model.addAttribute("list", list);

        return "history";
    }

    // 기록 삭제
    @GetMapping("/deleteHistory")
    public String history(@RequestParam String id, Model model) {

        List<HistoryDTO> list = historyService.deleteHistory(Long.parseLong(id));
        model.addAttribute("list", list);

        return "history";

    }
}
