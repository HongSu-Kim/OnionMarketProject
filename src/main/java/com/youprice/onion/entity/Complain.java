package com.youprice.onion.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Complain {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "compalain_id")
    private Integer id; // 신고번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 회원번호 FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; // 상품번호 FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private Chatroom chatroom; // 채팅방번호 FK

    private String complainType; // 신고유형
    private LocalDateTime complainDate; //신고일자
    private Integer complainUser; //신고자
    private String complainContent; // 신고내용
    private String status; // 처리상태

}