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

    private String origFileName;
    private String storeImageName;

    public ReviewImage(Review review, String origFileName, String storeImageName) {
        this.review = review;
        this.origFileName = origFileName;
        this.storeImageName = storeImageName;
    }
}