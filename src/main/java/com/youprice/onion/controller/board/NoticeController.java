package com.youprice.onion.controller.board;

import com.youprice.onion.dto.board.NoticeDTO;
import com.youprice.onion.dto.board.NoticeFormDTO;
import com.youprice.onion.dto.board.NoticeUpdateDTO;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.board.NoticeService;
import com.youprice.onion.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;
    private final MemberService memberService;

    @GetMapping("/created")
    public String createdForm(@ModelAttribute("form") NoticeFormDTO form, Model model,@LoginUser SessionDTO sessionDTO){
        if (sessionDTO != null) {
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            model.addAttribute("memberDTO", memberDTO);
        } else return "member/login";
        return "board/noticeForm";
    }

    @PostMapping("/created")
    public String createNotice(@Valid @ModelAttribute("form") NoticeFormDTO form, BindingResult bindingResult,
                               Model model, @LoginUser SessionDTO sessionDTO,
                                @RequestParam("noticeImageName")List<MultipartFile> noticeImageName) throws IOException {
        if(sessionDTO == null) return "member/login";
        if(bindingResult.hasErrors()){
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            model.addAttribute("memberDTO", memberDTO);
            return "board/noticeForm";
        }
        noticeService.saveNotice(form, noticeImageName);

        return "redirect:/notice/list";
    }

    @GetMapping("/list")
    public String lists(@PageableDefault(size=10, sort="id", direction = Sort.Direction.DESC) Pageable pageable
                        ,@LoginUser SessionDTO sessionDTO ,Model model
                        ,@RequestParam(required = false, defaultValue = "") String word) {
        if (sessionDTO != null) {
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            model.addAttribute("memberDTO", memberDTO);
        }
        Page<NoticeDTO> noticelist = noticeService.findTypeNotice(pageable);
        List<NoticeDTO> qnaList = noticeService.findTypeQna();

        if (word.length() != 0) {
            noticelist = noticeService.searchNotice(word, pageable);
        }

        int pageNumber = noticelist.getPageable().getPageNumber();
        int totalPages = noticelist.getTotalPages();
        int pageBlock = 5;
        int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("noticelist", noticelist);
        model.addAttribute("qnaList", qnaList);

        return "board/boardMain";
    }

    @GetMapping("/article/{id}")
    public String noticeArticle(@PathVariable Long id, Model model,@LoginUser SessionDTO sessionDTO,
                                @RequestParam(required = false, defaultValue = "") String word){
        if (sessionDTO != null) {
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            model.addAttribute("memberDTO", memberDTO);
        } else return "member/login";

        NoticeDTO noticeDTO = noticeService.findNoticeDTO(id);
        noticeService.updateView(id);

        model.addAttribute("noticeDTO", noticeDTO);
        model.addAttribute("word", word);

        return "board/noticeArticle";
    }

    @GetMapping("/update/{id}")
    public String noticeUpdateForm(@PathVariable Long id, Model model, @LoginUser SessionDTO sessionDTO){
        if (sessionDTO != null) {
            model.addAttribute("noticeDTO", noticeService.findNoticeDTO(id));
            model.addAttribute("memberDTO", memberService.getMemberDTO(sessionDTO.getId()));
            model.addAttribute("form", new NoticeUpdateDTO());
        } else return "redirect:/member/login";
        return "board/noticeUpdate";
    }

    @PostMapping ("/update/{id}")
    public String noticeUpdate(@PathVariable Long id, @Valid @ModelAttribute("form") NoticeUpdateDTO form,
                               BindingResult bindingResult, Model model, @LoginUser SessionDTO sessionDTO) throws IOException {
        if(bindingResult.hasErrors()){
            model.addAttribute("noticeDTO", noticeService.findNoticeDTO(id));
            model.addAttribute("memberDTO", memberService.getMemberDTO(sessionDTO.getId()));
            return "board/noticeUpdate";
        }
        noticeService.update(id, form);
        return"redirect:/notice/list";
    }

    @GetMapping("/image/delete/{imageId}/{noticeId}")
    public String noticeImageDelete(@PathVariable Long imageId, Long noticeId){
        noticeService.imageDelete(imageId);
        return "redirect:/notice/update/{noticeId}";
    }

    @GetMapping ("/delete/{id}")
    public String noticeDelete(@PathVariable Long id){
        noticeService.delete(id);
        return "redirect:/notice/list";
    }

}
