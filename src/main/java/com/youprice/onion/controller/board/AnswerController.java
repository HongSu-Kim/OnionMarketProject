package com.youprice.onion.controller.board;

import com.youprice.onion.dto.board.AnswerDTO;
import com.youprice.onion.dto.board.AnswerFormDTO;
import com.youprice.onion.dto.board.InquiryDTO;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.board.AnswerService;
import com.youprice.onion.service.board.InquiryService;
import com.youprice.onion.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("answer")
public class AnswerController {

    private final AnswerService answerService;
    private final InquiryService inquiryService;
    private final MemberService memberService;

    @GetMapping("/created/{id}")
    @PreAuthorize("isAuthenticated()")
    public String answerForm(@PathVariable("id") Long inquiryId, Model model,
                             @LoginUser SessionDTO sessionDTO){
        if(sessionDTO != null){
            InquiryDTO inquiryDTO = inquiryService.findInquiryDTO(inquiryId);
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            model.addAttribute("memberDTO", memberDTO);
            model.addAttribute("inquiryDTO", inquiryDTO);
        } else return "member/login";
        return "board/answerForm";
    }

    @PostMapping("created/{id}")
    public String createdAnswer(@PathVariable("id") Long inquiryId, Model model,
                                @Valid @ModelAttribute AnswerFormDTO answerFormDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            InquiryDTO inquiryDTO = inquiryService.findInquiryDTO(inquiryId);
            MemberDTO memberDTO = memberService.getMemberDTO(answerFormDTO.getMemberId());
            model.addAttribute("memberDTO",memberDTO);
            model.addAttribute("inquiryDTO",inquiryDTO);
            return "board/answerForm";
        }
        answerService.saveAnswer(answerFormDTO);
        return "redirect:/inquiry/article/" + inquiryId;
    }

    // 수정 화면
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model, @LoginUser SessionDTO sessionDTO){
        AnswerDTO answerDTO = answerService.findAnswerDTO(id);

        if(sessionDTO != null){
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            model.addAttribute("memberDTO", memberDTO);
        }
        model.addAttribute("answerDTO",answerDTO);
        return "board/answerForm";
    }
    // 수정
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long answerId,Model model,
                         @Valid @ModelAttribute AnswerFormDTO answerFormDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            AnswerDTO answerDTO = answerService.findAnswerDTO(answerId);
            MemberDTO memberDTO = memberService.getMemberDTO(answerFormDTO.getMemberId());
            model.addAttribute("memberDTO",memberDTO);
            model.addAttribute("answerDTO",answerDTO);
            return "board/answerForm";
        }

        Long inquiryId = answerService.findAnswerDTO(answerId).getInquiryId();
        answerService.updateAnswer(answerId, answerFormDTO);
        return "redirect:/inquiry/article/" + inquiryId;
    }

    // 삭제
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        AnswerDTO answerDTO = answerService.findAnswerDTO(id);
        answerService.deleteAnswer(answerDTO);

        return "redirect:/inquiry/article/" + answerDTO.getInquiryId();
    }
}
