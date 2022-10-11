package com.youprice.onion.entity.board;

import com.youprice.onion.entity.order.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "review_id")
    private Long id; // 리뷰번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; // 주문번호 FK

    private String reviewType; // 리뷰타입
    private String reviewContent; // 리뷰내용
    private Integer grade; // 평점
    private LocalDate reviewDate; //등록일

    /*@OneToMany(mappedBy = "review")
    private List<ReviewComment> reviewComment;*/

    @OneToMany(mappedBy = "review") // 이미지
    private List<ReviewImage> reviewImageName;

    public Review(Order order, String reviewContent, Integer grade, LocalDate reviewDate) {
        this.order = order;
        this.reviewContent = reviewContent;
        this.grade = grade;
        this.reviewDate = reviewDate;
    }

}
