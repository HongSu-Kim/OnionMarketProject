package com.youprice.onion.repository.board;

import com.youprice.onion.entity.board.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
    List<ReviewImage> findByReviewId(Long reviewId);
}
