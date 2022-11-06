package com.youprice.onion.controller.board;

import com.youprice.onion.dto.board.InquiryDTO;
import com.youprice.onion.dto.board.InquiryFormDTO;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.security.auth.LoginUser;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;
    private final MemberService memberService;
    private final ProhibitionKeywordService prohibitionKeywordService;

    @GetMapping("/created")
    public String inquiryForm(@ModelAttribute("form") InquiryFormDTO form, Model model,
                              @LoginUser SessionDTO sessionDTO) {
        if (sessionDTO != null) {
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            model.addAttribute("memberDTO", memberDTO);
        } else return "redirect:/member/login";

        return "board/inquiryForm";
    }

    @PostMapping("/created")
    public String createInquiry(@Valid @ModelAttribute("form") InquiryFormDTO form, BindingResult bindingResult,
                                @LoginUser SessionDTO sessionDTO, Model model) {
        if (bindingResult.hasErrors()) {
            if (sessionDTO != null) {
                MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
                model.addAttribute("memberDTO", memberDTO);
            } else return "redirect:/member/login";
            return "board/inquiryForm";
        }
        if (prohibitionKeywordService.ProhibitionKeywordFind(form.getInquirySubject())) { //금지키워가있으면 true
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
                        @RequestParam(required = false, defaultValue = "") String dt_fr,
                        @RequestParam(required = false, defaultValue = "") String dt_to,
                        @LoginUser SessionDTO sessionDTO, Model model) {
        if (sessionDTO != null) {
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            model.addAttribute("memberDTO", memberDTO);
        }
        Page<InquiryDTO> questionlist = inquiryService.findAll(pageable);

        if (word.length() != 0 && dt_fr.equals("")) {
            questionlist = inquiryService.getSearchList(field, word, pageable);
        } else if(word.length() == 0 && !dt_fr.equals("")){
            questionlist = inquiryService.allListByPeriod(dt_fr, dt_to, pageable);
        } else if(word.length() != 0 && !dt_fr.equals("")){
            questionlist = inquiryService.allListSearch(dt_fr, dt_to, field, word, pageable);
        }

        int pageNumber = questionlist.getPageable().getPageNumber();
        int totalPages = questionlist.getTotalPages();
        int pageBlock = 5;
        int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
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
                         @PathVariable Long memberId, Model model, @RequestParam(required = false) String dt_fr,
                         @RequestParam(required = false) String dt_to) {
        Page<InquiryDTO> questionlist = inquiryService.MemberReviewList(memberId, pageable);
        if(dt_fr != null){
            questionlist = inquiryService.getPeriodSearch(dt_fr, dt_to, memberId, pageable);
        }

        int pageNumber = questionlist.getPageable().getPageNumber();
        int totalPages = questionlist.getTotalPages();
        int pageBlock = 5;
        int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("questionlist", questionlist);
        model.addAttribute("memberId", memberId);

        return "board/myInquiry";
    }

    // 문의 상세화면
    @GetMapping("/article/{id}")
    public String article(@PathVariable long id, Model model,
                          @RequestParam(required = false, defaultValue = "") String field,
                          @RequestParam(required = false, defaultValue = "") String word,
                          @LoginUser SessionDTO sessionDTO) {
        if (sessionDTO != null) {
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            model.addAttribute("memberDTO", memberDTO);
        } else return "member/login";

        InquiryDTO inquiryDTO = inquiryService.findInquiryDTO(id);

        model.addAttribute("inquiryDTO", inquiryDTO);
        model.addAttribute("field", field);
        model.addAttribute("word", word);

        return "board/inquiryArticle";
    }

    // 수정 화면
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, @LoginUser SessionDTO sessionDTO, Model model,
                             @ModelAttribute("form") InquiryFormDTO form) {
        if (sessionDTO != null) {
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            model.addAttribute("memberDTO", memberDTO);
        } else return "member/login";

        InquiryDTO inquiryDTO = inquiryService.findInquiryDTO(id);
        model.addAttribute("inquiryDTO", inquiryDTO);

        return "board/inquiryUpdate";
    }

    // 수정
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("form") InquiryFormDTO form,
                         BindingResult bindingResult, @LoginUser SessionDTO sessionDTO, Model model) {
        InquiryDTO inquiryDTO = inquiryService.findInquiryDTO(id);

        if (bindingResult.hasErrors()) {
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            model.addAttribute("memberDTO", memberDTO);
            model.addAttribute("inquiryDTO", inquiryDTO);
            return "board/inquiryUpdate";
        }
        inquiryService.updateInquiry(id, form);
        return "redirect:/inquiry/article/{id}";
    }

    // 삭제
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {

        InquiryDTO inquiryDTO = inquiryService.findInquiryDTO(id);
        inquiryService.deleteInquiry(inquiryDTO);

        return "redirect:/inquiry/list";
    }

}
