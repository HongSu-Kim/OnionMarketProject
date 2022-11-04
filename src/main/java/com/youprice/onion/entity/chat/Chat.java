package com.youprice.onion.entity.chat;

import com.youprice.onion.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Chat{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "chat_id")
    private Long id; //채팅번호(각 채팅 문장의 번호) PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="chatroom_id")
    private Chatroom chatroom; //채팅방번호 FK

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member; //회원번호 FK

    private String message; //메세지
    private String chatImageName; //첨부이미지
    private boolean read; //읽음 표시
    private LocalDateTime sendingTime; //전송 시간

	public Chat(Chatroom chatroom, Member member, String message, String chatImageName) {
		this.chatroom = chatroom;
		this.member = member;
		this.message = message;
		this.chatImageName = chatImageName;
		read = false;
		sendingTime = LocalDateTime.now();
	}

}
