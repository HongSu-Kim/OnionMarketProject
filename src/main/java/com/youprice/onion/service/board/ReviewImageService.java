package com.youprice.onion.service.board;

import com.youprice.onion.dto.board.ReviewImageDTO;

import java.util.List;

public interface ReviewImageService {
    List<ReviewImageDTO> findByReviewId(Long reviewId);
}
