package com.youprice.onion.entity.chat;

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

    private String message; //메세지
    private String chat_image_name; //첨부 이미지
    private char read_or_not; //읽음 표시
    private LocalDateTime sending_time; //전송 시간

}
