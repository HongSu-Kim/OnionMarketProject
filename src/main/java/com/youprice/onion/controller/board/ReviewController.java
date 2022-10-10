package com.youprice.onion.controller.board;

import com.youprice.onion.dto.order.OrderDTO;
import com.youprice.onion.entity.board.Review;
import com.youprice.onion.entity.order.Order;
import com.youprice.onion.service.board.ReviewService;
import com.youprice.onion.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;
    private final OrderService orderService;

    @GetMapping("/created")
    public String createdForm() {
        return "board/reviewForm";
    }

    @PostMapping("/created")
    public String created(@Valid @RequestParam("grade") Integer grade,
                          @Valid @RequestParam("reviewContent") String reviewContent,
                          @Valid @RequestParam("reviewImageName") List<MultipartFile> reviewImageName) throws IOException {

        Order order = orderService.getOrderDTO(1L);

        Review review = new Review(order, reviewContent, grade, LocalDate.now());
        Long reviewId = reviewService.save(review);
        reviewService.addReview(reviewId, reviewImageName);

        return "board/reviewForm";
    }

    @GetMapping("/image")
    public String uploadForm() {
        return "board/reviewUpload";
    }
}