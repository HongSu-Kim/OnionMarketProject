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

    @OneToOne
    @JoinColumn(name="chat_room_id")
    private ChatRoom chatroom; //채팅방번호 FK

    @Column
    private String message; //메세지

    @Column
    private String chat_image_name; //첨부 이미지

    @Column
    private char read_or_not; //읽음 표시

    @Column
    private LocalDateTime sending_time; //전송 시간


}
