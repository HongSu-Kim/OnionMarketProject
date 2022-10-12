package com.youprice.onion.controller.board;

import com.youprice.onion.dto.board.InquiryDTO;
import com.youprice.onion.dto.board.InquiryFormDTO;
import com.youprice.onion.service.board.InquiryService;
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

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;
    private final MemberService memberService;

    // 저장
    @GetMapping("/created")
    public String inquiryForm(@ModelAttribute InquiryFormDTO inquiryFormDTO){
        return "board/inquiryForm";
    }
    @PostMapping("/created")
    public String createInquiry(@Valid @ModelAttribute InquiryFormDTO form, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "board/inquiryForm";
        }
        inquiryService.saveInquiry(form);
        return "redirect:/inquiry/list";
    }

    // 목록
    @GetMapping("/list")
    public String lists(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") String field,
                        @RequestParam(required = false, defaultValue = "") String word, Model model) {

        Page<InquiryDTO> questionlist = inquiryService.findAll(pageable);

        if(field.equals("name")) {
            questionlist = inquiryService.findByUsernameContaining(word, pageable);
        } else if(field.equals("memberInfo")) {
            questionlist = inquiryService.findByTypeContaining("회원정보",word, pageable);
        } else if(field.equals("purchase")) {
            questionlist = inquiryService.findByTypeContaining("거래",word, pageable);
        } else if(field.equals("etc")) {
            questionlist = inquiryService.findByTypeContaining("기타서비스",word,pageable);
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

    // 문의 상세화면
    @GetMapping("/article/{id}")
    public String article(@PathVariable long id, Model model,
                          @RequestParam(required = false, defaultValue = "") String field,
                          @RequestParam(required = false, defaultValue = "") String word){

        InquiryDTO inquiryDTO = inquiryService.findInquiryDTO(id);

        model.addAttribute("inquiryDTO", inquiryDTO);
        model.addAttribute("field", field);
        model.addAttribute("word", word);

        return "board/inquiryArticle";
    }
    // 수정 화면
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){

        InquiryDTO inquiryDTO = inquiryService.findInquiryDTO(id);

        model.addAttribute("inquiryDTO",inquiryDTO);
        return "board/inquiryUpdate";
    }
    // 수정
    @PostMapping("/update/{id}")
    public String inquiryUpdate(@PathVariable("id") Long id,
                                @Valid @ModelAttribute InquiryFormDTO form, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "board/inquiryUpdate";
        }
        inquiryService.update(id, form);

        return "redirect:/inquiry/list";
    }
}
