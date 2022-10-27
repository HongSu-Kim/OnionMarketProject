package com.youprice.onion.dto.board;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.entity.board.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDTO {

    private Long reviewId; // 리뷰번호 PK
    private Long orderId;
    private Long memberId;
    private String reviewContent; // 리뷰내용
    private Integer grade; // 평점
    private LocalDate reviewDate; //등록일
    private Long salesId;
    private List<ReviewImageDTO> reviewImageList;

    private MemberDTO memberDTO;
    public ReviewDTO(Review review) {
        this.reviewId = review.getId();
        this.orderId = review.getOrder().getId();
        this.memberId = review.getMember().getId();
        this.reviewContent = review.getReviewContent();
        this.grade = review.getGrade();
        this.reviewDate = review.getReviewDate();
        this.salesId = review.getSalesId();
        this.reviewImageList = review.getReviewImageName().stream().map(ReviewImageDTO::new).collect(Collectors.toList());
        memberDTO = new MemberDTO(review.getMember());
    }
}
