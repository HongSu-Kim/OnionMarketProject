package com.youprice.onion.controller.board;

import com.youprice.onion.dto.board.ReviewDTO;
import com.youprice.onion.dto.board.ReviewFormDTO;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.service.board.ReviewService;
import com.youprice.onion.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final MemberService memberService;

    @GetMapping("/created")
    public String createdForm() {
        return "board/reviewForm";
    }

    @PostMapping("/created")
    public String created(@Valid @ModelAttribute ReviewFormDTO form, Model model,
                          @RequestParam("reviewImageName") List<MultipartFile> reviewImageName) throws IOException {
        reviewService.saveReview(form, reviewImageName);
        return "redirect:/review/list";
    }

    @GetMapping("/list")
    public String reviewLists(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                Model model) {
        MemberDTO memberDTO = memberService.getMemberDTO(1L);
        Page<ReviewDTO> reviewList = reviewService.findAll(pageable);

        int pageNumber = reviewList.getPageable().getPageNumber();
        int totalPages = reviewList.getTotalPages();
        int pageBlock = 5;
        int startBlockPage = ((pageNumber)/pageBlock)*pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("memberDTO", memberDTO);

        return "board/reviewList";
    }
    // 이미지출력
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource image(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + reviewService.filePath()+filename);
    }


}