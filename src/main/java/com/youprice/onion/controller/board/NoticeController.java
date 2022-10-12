package com.youprice.onion.controller.board;

import com.youprice.onion.dto.board.NoticeDTO;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.entity.board.Notice;
import com.youprice.onion.service.board.NoticeService;
import com.youprice.onion.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;
    private final MemberService memberService;

    @GetMapping("/created")
    public String createdForm(@ModelAttribute NoticeDTO noticeDTO, Model model){
        MemberDTO memberDTO = memberService.getMemberDTO(1L);
        model.addAttribute(memberDTO);
        return "board/noticeForm";
    }

    @PostMapping("/created")
    public String createNotice(@Valid @ModelAttribute NoticeDTO noticeDTO){

        noticeService.saveNotice(noticeDTO);

        return "redirect:/notice/list";
    }

    @GetMapping("/list")
    public String lists(){

        return "board/noticeList";
    }



}
