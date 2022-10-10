package com.youprice.onion.service.board;

import com.youprice.onion.entity.board.Review;
import com.youprice.onion.entity.board.ReviewImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Long save(Review review);
    void addReview(Long reviewId, List<MultipartFile> imageFiles) throws IOException;
    Optional<Review> findByUserId(String userId);
    public Review findById(Long id);
    public List<Review> findAll();

    // 사진
    List<ReviewImage> storeImages(Long reviewId, List<MultipartFile> multipartFiles) throws IOException;
    String storePath(MultipartFile multipartFile) throws IOException;
}
