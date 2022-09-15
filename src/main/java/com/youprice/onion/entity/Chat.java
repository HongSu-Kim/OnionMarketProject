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
    private Long id; //각각 채팅 내용의 번호

    @Column
    private int chat_room_id; //채팅방의 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private String sender;

    @Column
    private String message;

    @Column
    private String image_file_name;

    @Column
    private char read_or_not;

    @Column
    private LocalDateTime sending_time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

}
