package com.youprice.onion.controller.board;

import com.youprice.onion.dto.board.ReviewFormDTO;
import com.youprice.onion.service.board.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/created")
    public String createdForm() {
        return "board/reviewForm";
    }

    @PostMapping("/created")
    public String created(@Valid @ModelAttribute ReviewFormDTO form,
                          @RequestParam("reviewImageName") List<MultipartFile> reviewImageName) throws IOException {
        reviewService.saveReview(form, reviewImageName);
        return "board/reviewForm";
    }
}