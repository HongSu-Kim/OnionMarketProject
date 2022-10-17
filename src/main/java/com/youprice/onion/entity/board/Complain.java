package com.youprice.onion.entity.board;

import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.chat.Chatroom;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Complain {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "complain_id")
    private Long id; // 신고번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 신고자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; // 상품번호 FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private Chatroom chatroom; // 채팅방번호 FK

    private String complainType; // 신고유형
    private LocalDateTime complainDate; //신고일자
    private String complainContent; // 신고내용
    private String status; // 처리상태

    public Complain(Member member, Product product, Chatroom chatroom, String complainType,
                                                String complainContent, String status) {
        this.member = member;
        this.product = product;
        this.chatroom = chatroom;
        this.complainType = complainType;
        this.complainDate = LocalDateTime.now();
        this.complainContent = complainContent;
        this.status = status;
    }
    public void updateStatus(String status){
        this.status = status;
    }
}
