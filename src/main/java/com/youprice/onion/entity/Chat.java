package com.youprice.onion.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Chat{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "chat_id")
    private Long id; //채팅번호(각 채팅 문장의 번호) PK

    @ManyToOne
    @JoinColumn(name="chatroom_id")
    private Chatroom chatroom; //채팅방번호 FK

    @Column
    private String message; //메세지

    @Column
    private String chatImageName; //첨부 이미지

    @Column
    private char readOrNot; //읽음 표시

    @Column
    private LocalDateTime sendingTime; //전송 시간


}
