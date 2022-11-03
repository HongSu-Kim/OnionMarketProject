package com.youprice.onion.dto.board;

import com.youprice.onion.entity.board.ReviewImage;
import lombok.Getter;

@Getter
public class ReviewImageDTO {

    private Long id;
    private Long reviewId;
    private String storeImageName;

    public ReviewImageDTO(ReviewImage reviewImage) {
        this.id = reviewImage.getId();
        this.reviewId = reviewImage.getReview().getId();
        this.storeImageName = reviewImage.getStoreImageName();
    }
}
