package com.youprice.onion.controller.board;

import com.youprice.onion.dto.board.NoticeDTO;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.service.board.NoticeService;
import com.youprice.onion.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;
    private final MemberService memberService;

    @GetMapping("/created")
    public String createdForm(@ModelAttribute NoticeDTO noticeDTO, Model model){
        MemberDTO memberDTO = memberService.getMemberDTO(19L);
        model.addAttribute(memberDTO);
        return "board/noticeForm";
    }

    @PostMapping("/created")
    public String createNotice(@Valid @ModelAttribute NoticeDTO noticeDTO){
        System.out.println(noticeDTO.getNoticeType());
        noticeService.saveNotice(noticeDTO);

        return "redirect:/notice/list";
    }

    //공지 리스트
    @GetMapping("/list")
    public String lists(@PageableDefault(size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable
                        ,@RequestParam(required = false, defaultValue = "") String field
                        ,@RequestParam(required = false, defaultValue = "") String word
                        ,Model model){
        MemberDTO memberDTO = memberService.getMemberDTO(19l);

        Page<NoticeDTO> noticelist = noticeService.findAllNotice(pageable);

        /*
        if(word.length() != 0) {
            noticelist = noticeService.getSearchList(field,word, pageable);
        }
        */


        int pageNumber = noticelist.getPageable().getPageNumber();
        int totalPages = noticelist.getTotalPages();
        int pageBlock = 5;
        int startBlockPage = ((pageNumber)/pageBlock)*pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("noticelist", noticelist);
        model.addAttribute("memberDTO", memberDTO);

        return "board/noticeList";
    }

    //각 공지 내부 페이지
    @GetMapping("/article/{id}")
    public String noticeArticle(@PathVariable Long id, Model model,
                                @RequestParam(required = false, defaultValue = "") String field,
                                @RequestParam(required = false, defaultValue = "") String word){

        MemberDTO memberDTO = memberService.getMemberDTO(19l);
        NoticeDTO noticeDTO = noticeService.findNoticeDTO(id);

        model.addAttribute("noticeDTO", noticeDTO);
        model.addAttribute("field", field);
        model.addAttribute("word", word);
        model.addAttribute("memberDTO", memberDTO);

        return "board/noticeArticle";
    }

    //수정 화면으로 이동
    @GetMapping("/update/{id}")
    public String noticeUpdateForm(@PathVariable Long id, Model model){
        NoticeDTO noticeDTO = noticeService.findNoticeDTO(id);

        model.addAttribute("noticeDTO", noticeDTO);

        return "board/noticeUpdate";
    }

    //수정 실행
    @PostMapping("/update/{id}")
    public String noticeUpdate(@PathVariable Long id, NoticeDTO noticeDTO){
        noticeService.update(id, noticeDTO);
        return"";
    }

    //삭제 실행
    @GetMapping ("/delete/{id}")
    public String noticeDelete(@PathVariable Long id){

        noticeService.delete(id);

        return "redirect:/notice/list";
    }


}
