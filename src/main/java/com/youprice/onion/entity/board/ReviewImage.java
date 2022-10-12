package com.youprice.onion.entity.board;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class ReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "review_image_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review; // 리뷰번호 FK

    private String originalFileName;
    private String storeImageName;

    public ReviewImage(Review review, String originalFileName, String storeImageName) {
        this.review = review;
        this.originalFileName = originalFileName;
        this.storeImageName = storeImageName;
    }
}