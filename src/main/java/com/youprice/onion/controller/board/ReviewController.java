package com.youprice.onion.controller.board;

import com.youprice.onion.dto.board.*;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.order.OrderDTO;
import com.youprice.onion.dto.product.ProductImageDTO;
import com.youprice.onion.entity.product.ProductImage;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.board.ReviewImageService;
import com.youprice.onion.service.board.ReviewService;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.order.OrderService;
import com.youprice.onion.service.product.ProductImageService;
import com.youprice.onion.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewImageService reviewImageService;
    private final OrderService orderService;
    private final ProductService productService;
    private final ProductImageService productImageService;
    private final MemberService memberService;

    @GetMapping("/created/{orderId}")
    public String createdForm(@PathVariable("orderId") Long orderId, Model model,
                              @LoginUser SessionDTO sessionDTO) {
        OrderDTO orderDTO = orderService.getOrderDTO(orderId);
        if(sessionDTO != null){
            model.addAttribute("sessionDTO", sessionDTO);
        }
        //ProductDTO productDTO = productService.findById(orderDTO.getProductId()).orElse(null);
        //ProductImageDTO productImageDTO = productImageService.findByProduct_ProductId(orderDTO.getProductId()).get(0);

        model.addAttribute("orderDTO", orderDTO);
        model.addAttribute("sessionDTO", sessionDTO);
        //model.addAttribute("productImageDTO", productImageDTO);
        //model.addAttribute("productDTO", productDTO);
        return "board/reviewForm";
    }
    // 상품이미지 1개 보이기
    @ResponseBody
    @GetMapping("/productImage/{productImageName}")
    public Resource reviewFormProduct(@PathVariable String productImageName) throws MalformedURLException {
        return new UrlResource("file:" + reviewService.filePath() + productImageName);
    }

    @PostMapping("/created/{orderId}")
    public String created(@Valid @ModelAttribute ReviewFormDTO form, @PathVariable("orderId") Long orderId, Model model,
                          @RequestParam("reviewImageName") List<MultipartFile> reviewImageName) throws IOException {
        reviewService.saveReview(form, reviewImageName);
        return "redirect:/review/list";
    }

    @GetMapping("/list")
    public String reviewLists(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                Model model, @LoginUser SessionDTO sessionDTO) {
        Page<ReviewDTO> reviewList = reviewService.findAll(pageable);
        if(sessionDTO != null){
            model.addAttribute("sessionDTO", sessionDTO);
        }
        int pageNumber = reviewList.getPageable().getPageNumber();
        int totalPages = reviewList.getTotalPages();
        int pageBlock = 5;
        int startBlockPage = ((pageNumber)/pageBlock)*pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("reviewList", reviewList);

        return "board/reviewList";
    }
    // 이미지출력
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource image(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + reviewService.filePath()+filename);
    }

    // 수정 화면
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model,@LoginUser SessionDTO sessionDTO){
        ReviewDTO reviewDTO = reviewService.findReviewDTO(id);
        List<ReviewImageDTO> imageList = reviewImageService.findByReviewId(id);

        if(sessionDTO != null){
            model.addAttribute("sessionDTO", sessionDTO);
        }
        model.addAttribute("reviewDTO",reviewDTO);
        model.addAttribute("imageList",imageList);
        model.addAttribute("sessionDTO",sessionDTO);
        return "board/reviewUpdate";
    }
    // 첨부사진 개별 삭제
    @GetMapping("/images/delete/{id}/{reviewId}")
    public String imageDelete(@PathVariable("id") Long imageId, Long reviewId){
        reviewImageService.deleteImage(imageId);
        return "redirect:/review/update/{reviewId}";
    }
    // 수정
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid @ModelAttribute ReviewUpdateDTO form, BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors()){
            return "board/reviewUpdate";
        }
        reviewService.updateReview(id, form);
        return "redirect:/review/list";
    }

    // 삭제
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        ReviewDTO reviewDTO = reviewService.findReviewDTO(id);
        reviewService.deleteReview(reviewDTO);

        return "redirect:/review/list";
    }

}