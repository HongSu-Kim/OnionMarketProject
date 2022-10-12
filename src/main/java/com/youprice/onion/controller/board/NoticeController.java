package com.youprice.onion.controller.board;

import com.youprice.onion.service.board.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/created")
    public String createdForm(){

        return "board/noticeForm";
    }

    @PostMapping("/created")
    public String createNotice(){

        return "redirect:/notice/list";
    }

    @GetMapping("/list")
    public String lists(){

        return "board/noticeList";
    }



}
