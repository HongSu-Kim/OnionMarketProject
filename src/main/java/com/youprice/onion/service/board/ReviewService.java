package com.youprice.onion.service.board;

import com.youprice.onion.dto.board.ReviewDTO;
import com.youprice.onion.dto.board.ReviewFormDTO;
import com.youprice.onion.dto.board.ReviewImageDTO;
import com.youprice.onion.dto.board.ReviewUpdateDTO;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.order.OrderDTO;
import com.youprice.onion.entity.board.ReviewImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReviewService {
    ReviewDTO getReviewDTO(Long reviewId);
    int saveReview(ReviewFormDTO form, List<MultipartFile> reviewImageName) throws IOException;
    ReviewDTO findByUserId(String userId);
    public ReviewDTO findReviewDTO(Long reviewId);
    MemberDTO getSalesUserName(OrderDTO orderDTO);
    Page<ReviewDTO> userReviewList(Long salesId, Pageable pageable);
    Page<ReviewDTO> findAll(Pageable pageable);
    List<ReviewDTO> findAll();
    void updateReview(Long reviewId, ReviewUpdateDTO form) throws IOException;
    void deleteReview(ReviewDTO reviewDTO);

    // 사진
    String filePath();
    List<ReviewImage> storeImages(Long reviewId, List<MultipartFile> multipartFiles) throws IOException;
    String storePath(MultipartFile multipartFile) throws IOException;

    double avgGrade(Long salesId);
}
