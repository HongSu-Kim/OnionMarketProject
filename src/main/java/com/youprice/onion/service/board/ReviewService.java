package com.youprice.onion.service.board;

import com.youprice.onion.dto.board.ReviewDTO;
import com.youprice.onion.dto.board.ReviewFormDTO;
import com.youprice.onion.dto.board.ReviewUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReviewService {
    ReviewDTO getReviewDTO(Long reviewId);
    int saveReview(ReviewFormDTO form, List<MultipartFile> reviewImageList) throws IOException;
    ReviewDTO findReviewDTO(Long reviewId);
    Page<ReviewDTO> userReviewList(Long salesId, Pageable pageable);
    Page<ReviewDTO> myReviewList(Long memberId, Pageable pageable);
    Page<ReviewDTO> findAll(Pageable pageable);
    void updateReview(Long reviewId, ReviewUpdateDTO form) throws IOException;
    void deleteReview(Long reviewId);
    void deleteImage(Long imageId);

}
