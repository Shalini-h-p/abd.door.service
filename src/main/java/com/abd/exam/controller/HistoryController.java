package com.abd.exam.controller;

import com.abd.exam.Service.HistoryService;
import com.abd.exam.dto.LogDto;
import com.abd.exam.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping("/history")
    public String getHistories(Model model) {
        List<History> result = historyService.getHistories();
        model.addAttribute("histories", result);
        return "history";
    }

    // Add log
    @GetMapping("/log")
    public String log(Model model) {
        LogDto log  = new LogDto();
        log.setType("ENTER");
        model.addAttribute("logDto", log);
        return "log";
    }

    @PostMapping("/log")
    public String log(@Valid LogDto logDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "log";
        }
        Optional<History> updateResult = historyService.addHistory(logDto);
        if(updateResult.isPresent()) {
            return "redirect:/history";
        }
        return "log";
    }
}
