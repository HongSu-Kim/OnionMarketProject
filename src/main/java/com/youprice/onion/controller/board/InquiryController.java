package com.youprice.onion.controller.board;

import com.youprice.onion.dto.board.AnswerDTO;
import com.youprice.onion.dto.board.InquiryDTO;
import com.youprice.onion.dto.board.InquiryFormDTO;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.board.AnswerService;
import com.youprice.onion.service.board.InquiryService;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.member.ProhibitionKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;
    private final AnswerService answerService;
    private final MemberService memberService;
    private final ProhibitionKeywordService prohibitionKeywordService;

    // 저장
    @GetMapping("/created")
    public String inquiryForm(Model model, @LoginUser SessionDTO sessionDTO){
        if(sessionDTO != null){
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            model.addAttribute("memberDTO", memberDTO);
        } else {
            return "member/login";
        }
        return "board/inquiryForm";
    }
    @PostMapping("/created")
    public String createInquiry(@Valid @ModelAttribute("form") InquiryFormDTO form, BindingResult bindingResult,
                                @LoginUser SessionDTO sessionDTO, Model model){
        if (bindingResult.hasErrors()) {
            if(sessionDTO != null){
                MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
                model.addAttribute("memberDTO", memberDTO);
            }
            return "board/inquiryForm";
        }
        if(prohibitionKeywordService.ProhibitionKeywordFind(form.getInquirySubject()) ) { //금지키워가있으면 true
            bindingResult.addError(new FieldError("form", "inquirySubject", "금지어입니다 다시입력!"));
        }

        inquiryService.saveInquiry(form);
        return "redirect:/inquiry/list";
    }

    // 목록
    @GetMapping("/list")
    public String lists(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") String field,
                        @RequestParam(required = false, defaultValue = "") String word,
                        @LoginUser SessionDTO sessionDTO, Model model) {
        if(sessionDTO != null){
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            model.addAttribute("memberDTO", memberDTO);
        }

        Page<InquiryDTO> questionlist = inquiryService.findAll(pageable);
        if(word.length() != 0) {
            questionlist = inquiryService.getSearchList(field,word, pageable);
        }

        int pageNumber = questionlist.getPageable().getPageNumber();
        int totalPages = questionlist.getTotalPages();
        int pageBlock = 5;
        int startBlockPage = ((pageNumber)/pageBlock)*pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("questionlist", questionlist);

        return "board/inquiryList";
    }
    // 나의 문의 목록
    @GetMapping("/myList/{memberId}")
    public String myList(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                         @PathVariable Long memberId, Model model){
        Page<InquiryDTO> questionlist = inquiryService.MemberReviewList(memberId, pageable);
        model.addAttribute("questionlist", questionlist);
        return "board/myInquiry";
    }

    // 문의 상세화면
    @GetMapping("/article/{id}")
    public String article(@PathVariable long id, Model model,
                          @RequestParam(required = false, defaultValue = "") String field,
                          @RequestParam(required = false, defaultValue = "") String word,
                          @LoginUser SessionDTO sessionDTO){
        if(sessionDTO != null){
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            model.addAttribute("memberDTO", memberDTO);
        } else { return "member/login";}
        InquiryDTO inquiryDTO = inquiryService.findInquiryDTO(id);

        model.addAttribute("inquiryDTO", inquiryDTO);
        model.addAttribute("field", field);
        model.addAttribute("word", word);

        return "board/inquiryArticle";
    }
    // 수정 화면
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, @LoginUser SessionDTO sessionDTO, Model model){
        if(sessionDTO != null){
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            model.addAttribute("memberDTO", memberDTO);
        } else {
            return "member/login";
        }
        InquiryDTO inquiryDTO = inquiryService.findInquiryDTO(id);

        model.addAttribute("inquiryDTO",inquiryDTO);
        return "board/inquiryUpdate";
    }
    // 수정
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,@Valid @ModelAttribute("form") InquiryFormDTO form,
                         BindingResult bindingResult, @LoginUser SessionDTO sessionDTO, Model model){
        InquiryDTO inquiryDTO = inquiryService.findInquiryDTO(id);

        if(bindingResult.hasErrors()){
            if(sessionDTO != null){
                MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
                model.addAttribute("memberDTO", memberDTO);
            }
            model.addAttribute("inquiryDTO", inquiryDTO);
            return "board/inquiryUpdate";
        }
        inquiryService.updateInquiry(id, form);
        return "redirect:/inquiry/article/{id}";
    }

    // 삭제
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){

        InquiryDTO inquiryDTO = inquiryService.findInquiryDTO(id);
        inquiryService.deleteInquiry(inquiryDTO);

        return "redirect:/inquiry/list";
    }
}
