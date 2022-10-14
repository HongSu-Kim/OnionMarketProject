package com.youprice.onion.entity.board;

import com.youprice.onion.dto.board.ReviewFormDTO;
import com.youprice.onion.dto.board.ReviewUpdateDTO;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.order.Order;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;//회원번호 FK

    private String reviewContent; // 리뷰내용
    private Integer grade; // 평점
    private LocalDate reviewDate; //등록일
    private Long salesId; // 판매자

    /*@OneToMany(mappedBy = "review")
    private List<ReviewComment> reviewComment;*/

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL) // 이미지
    private List<ReviewImage> reviewImageName;

    public Review(Order order, Member member, String reviewContent, Integer grade, Long salesId) {
        this.order = order;
        this.member = member;
        this.reviewContent = reviewContent;
        this.grade = grade;
        this.reviewDate = LocalDate.now();
        this.salesId = salesId;
    }

    public void updateReview(Long id, ReviewUpdateDTO form){
        this.id = id;
        this.reviewContent = form.getReviewContent();
        this.grade = form.getGrade();
        this.reviewDate = LocalDate.now();
    }
}
