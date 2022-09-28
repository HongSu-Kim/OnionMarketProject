package com.youprice.onion.entity.chat;

import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Product;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Chatroom{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "chatroom_id")
    private Long id;//채팅방번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; //회원번호 FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //상품번호 FK

    private String state; //채팅의 진행 상태, 진행중 - 종료 - 삭제(DB삭제 X)
    private LocalDateTime createDate; //생성시간
    private LocalDateTime modifyDate; //수정시간(마지막 채팅 시간)


    @OneToMany(mappedBy = "chatroom")
    private List<Chat> chatList;

}
