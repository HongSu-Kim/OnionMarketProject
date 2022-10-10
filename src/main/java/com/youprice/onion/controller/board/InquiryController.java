package com.youprice.onion.controller.board;

import com.youprice.onion.dto.board.InquiryDTO;
import com.youprice.onion.entity.board.Inquiry;
import com.youprice.onion.entity.member.Member;
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
import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;
    private final MemberService memberService;

    // 저장
    @GetMapping("/created")
    public String inquiryForm(@ModelAttribute("inquiryDTO") InquiryDTO inquiryDTO, Model model){
        Member member = memberService.findById(1L);
        model.addAttribute("memberDTO", member);
        return "board/inquiryForm";
    }
    @PostMapping("/created")
    public String createInquiry(@Valid @ModelAttribute("inquiryDTO") InquiryDTO inquiryDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) { //에러가 있으면
            return "board/inquiryForm";
        }
        Member member = memberService.findById(1L);

        Inquiry inquiry = new Inquiry(member, inquiryDTO.getInquiryType(), inquiryDTO.getDetailType(),
                inquiryDTO.getInquirySubject(), inquiryDTO.getInquiryContent(), LocalDate.now(), inquiryDTO.isSecret());
        inquiryService.save(inquiry);
        return "redirect:/inquiry/list";
    }

    // 목록
    @GetMapping("/list")
    public String lists(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") String field,
                        @RequestParam(required = false, defaultValue = "") String word) {

        Member member = memberService.findById(1L);

        Page<Inquiry> questionlist = inquiryService.findAll(pageable);

        if(field.equals("name")) {
            questionlist = inquiryService.findByUsernameContaining(word, pageable);
        }else if(field.equals("email")){
        }

        int pageNumber = questionlist.getPageable().getPageNumber();
        int totalPages = questionlist.getTotalPages();
        int pageBlock = 5;
        int startBlockPage = ((pageNumber)/pageBlock)*pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("memberDTO", member);
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

        Inquiry inquiry = inquiryService.findById(id);

        model.addAttribute("inquiry", inquiry);
        model.addAttribute("field", field);
        model.addAttribute("word", word);

        return "board/inquiryArticle";
    }
    // 수정 화면
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        Inquiry inquiry = inquiryService.findById(id);
        /*if(!inquiry.getMember().getName().equals(principal.getName())) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다");
        }*/
        model.addAttribute("inquiry",inquiry);
        return "board/inquiryUpdate";
    }
    // 수정
    @PostMapping("/update/{id}")
    public String inquiryUpdate(@PathVariable("id") Long id,
                                @Valid @ModelAttribute InquiryDTO inquiryDTO, BindingResult bindingResult){
        Inquiry inquiry = inquiryService.findById(id);
        if(bindingResult.hasErrors()){
            return "board/inquiryUpdate";
        }
        inquiryService.update(inquiryDTO);
        return "redirect:/inquiry/list";
    }
}
