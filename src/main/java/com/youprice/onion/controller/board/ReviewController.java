package com.youprice.onion.controller.board;

import com.youprice.onion.dto.board.*;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.order.OrderDTO;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.board.ReviewService;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.order.OrderService;
import com.youprice.onion.util.AlertRedirect;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final OrderService orderService;
    private final MemberService memberService;

    @GetMapping("/created/{orderId}")
    @PreAuthorize("isAuthenticated()")
    public String createdForm(@PathVariable("orderId") Long orderId, Model model,
                              @LoginUser SessionDTO sessionDTO) {
        OrderDTO orderDTO = orderService.getOrderDTO(orderId);

        if (sessionDTO != null) {
            MemberDTO salesDTO = memberService.getMemberDTO(orderDTO.getProductDTO().getMemberId());

            model.addAttribute("orderDTO", orderDTO);
            model.addAttribute("salesDTO", salesDTO);
        } else return "redirect:/member/login";

        return "board/reviewForm";
    }

    @PostMapping("/created/{orderId}")
    public String created(@Valid @ModelAttribute("form") ReviewFormDTO form, BindingResult bindingResult, @PathVariable("orderId") Long orderId,
                                @RequestParam("reviewImageName") List<MultipartFile> reviewImageName,
                                    Model model, HttpServletResponse response) throws IOException {
        OrderDTO orderDTO = orderService.getOrderDTO(orderId);

        if (bindingResult.hasErrors()) {
            model.addAttribute("orderDTO", orderDTO);
            return "board/reviewForm";
        }
        int point = reviewService.saveReview(form, reviewImageName);

        if(point != 0) {
            return AlertRedirect.warningMessage(response, "/review/mylist/"+form.getMemberId(), point + "포인트가 적립되었습니다.");
        }
        return "redirect:/";
    }

    // 특정회원이 받은 후기 목록
    @GetMapping("/list/{salesId}")
    public String userReviewList(@PathVariable Long salesId, Model model, @PageableDefault(size = 7) Pageable pageable) {
        MemberDTO memberDTO = memberService.getMemberDTO(salesId);
        Page<ReviewDTO> reviewList = reviewService.userReviewList(salesId, pageable);

        int pageNumber = reviewList.getPageable().getPageNumber();
        int totalPages = reviewList.getTotalPages();
        int pageBlock = 5;
        int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("memberDTO", memberDTO);

        return "board/reviewList";
    }

    // 내가 작성한 후기 목록
    @GetMapping("/mylist/{memberId}")
    @PreAuthorize("isAuthenticated()")
    public String myReviewList(@PathVariable Long memberId, Model model, @PageableDefault(size = 5) Pageable pageable) {

        MemberDTO memberDTO = memberService.getMemberDTO(memberId);
        Page<ReviewDTO> reviewList = reviewService.myReviewList(memberId, pageable);

        int pageNumber = reviewList.getPageable().getPageNumber();
        int totalPages = reviewList.getTotalPages();
        int pageBlock = 5;
        int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("memberDTO", memberDTO);

        return "board/myreviewList";
    }

    // 수정 화면
    @GetMapping("/update/{memberId}/{reviewId}")
    public String updateForm(@PathVariable Long memberId, @PathVariable Long reviewId, Model model,
                             @LoginUser SessionDTO sessionDTO) {
        if(sessionDTO == null) return "redirect:/member/login";
        ReviewDTO reviewDTO = reviewService.findReviewDTO(reviewId);

        model.addAttribute("reviewDTO", reviewDTO);
        return "board/reviewUpdate";
    }
    // 수정
    @PostMapping("/update/{memberId}/{reviewId}")
    public String update(@PathVariable Long memberId, @PathVariable Long reviewId,
                            @Valid @ModelAttribute("form") ReviewUpdateDTO form,
                            BindingResult bindingResult, Model model) throws IOException {
        ReviewDTO reviewDTO = reviewService.findReviewDTO(reviewId);

        if (bindingResult.hasErrors()) {
            model.addAttribute("reviewDTO", reviewDTO);
            return "board/reviewUpdate";
        }
        reviewService.updateReview(reviewId, form);
        return "redirect:/review/mylist/{memberId}";
    }

    // 첨부사진 개별 삭제
    @GetMapping("/images/delete/{memberId}/{imageId}/{reviewId}")
    public String imageDelete(@PathVariable Long memberId,@PathVariable Long imageId,
                              @PathVariable Long reviewId) {
        reviewService.deleteImage(imageId);
        return "redirect:/review/update/{memberId}/{reviewId}";
    }

    // 삭제
    @GetMapping("/delete/{memberId}/{reviewId}")
    public String delete(@PathVariable Long memberId,@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return "redirect:/review/mylist/{memberId}";
    }
}