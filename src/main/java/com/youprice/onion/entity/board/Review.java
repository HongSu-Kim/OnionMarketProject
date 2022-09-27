package com.youprice.onion.entity.board;

import com.youprice.onion.entity.order.Order;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "review_id")
    private Long id; // 리뷰번호 PK

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; // 주문번호 FK

    private String reviewType; // 리뷰타입
    private String reviewContent; // 리뷰내용
    private Integer grade; // 평점
    private LocalDateTime reviewDate; //등록일
    private String reviewImageName; // 첨부사진
}
