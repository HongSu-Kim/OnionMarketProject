package com.youprice.onion.service.board;

import com.youprice.onion.dto.board.ReviewDTO;
import com.youprice.onion.dto.board.ReviewFormDTO;
import com.youprice.onion.dto.board.ReviewImageDTO;
import com.youprice.onion.entity.board.ReviewImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReviewService {
    ReviewDTO getReviewDTO(Long reviewId);
    Long saveReview(ReviewFormDTO form, List<MultipartFile> reviewImageName) throws IOException;
    Long findSellerId(Long id);
    ReviewDTO findByUserId(String userId);
    public ReviewDTO findReviewDTO(Long id);
    //List<ReviewDTO> userReviewList(Long buyerId, Long reviewId);
    List<ReviewDTO> findAllReview();
//    List<ReviewImageDTO> imageList(Long reviewId);

    // 사진
    String filePath();
    List<ReviewImage> storeImages(Long reviewId, List<MultipartFile> multipartFiles) throws IOException;
    String storePath(MultipartFile multipartFile) throws IOException;
}
