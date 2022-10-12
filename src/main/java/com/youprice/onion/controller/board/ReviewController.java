package com.youprice.onion.controller.board;

import com.youprice.onion.dto.board.ReviewDTO;
import com.youprice.onion.dto.board.ReviewFormDTO;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.service.board.ReviewService;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.order.OrderService;
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
    public String reviewLists(Model model) {
        MemberDTO memberDTO = memberService.getMemberDTO(1L);
        List<ReviewDTO> reviewList = reviewService.findAllReview();

        //List<ReviewDTO> reviewList = reviewService.userReviewList(1L,reviewId);

        model.addAttribute("reviewList",reviewList);
        model.addAttribute("memberDTO",memberDTO);

        return "board/reviewList";
    }
    // 이미지출력
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource image(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + reviewService.filePath()+filename);
    }


}