package com.youprice.onion.entity;

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
    private Integer id;//채팅방번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; //회원번호 FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //상품번호 FK

    @Column
    private String state; //채팅의 진행 상태, 진행중 - 종료 - 삭제(DB삭제 X)

    @Column
    private LocalDateTime createDate = LocalDateTime.now(); //생성시간

    @Column
    private LocalDateTime modifyDate; //수정시간(마지막 채팅 시간)

    @OneToMany(mappedBy = "chatroom")
    private List<Chat> chatList;

}
