package com.youprice.onion.dto.chat;

import com.youprice.onion.entity.chat.Chat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ChatDTO {

	private Long chatId; //채팅번호(각 채팅 문장의 번호) PK
	private Long chatroomId; //채팅방번호 FK
	private Long memberId;
	private String memberNickname;
	private String message; //메세지
	private String chatImageName; //첨부이미지
	private boolean readOrNot; //읽음 표시
	private LocalDateTime sendingTime; //전송 시간

	public ChatDTO(Chat chat) {
		chatId = chat.getId();
		chatroomId = chat.getChatroom().getId();
		memberId = chat.getMember().getId();
		memberNickname = chat.getMember().getNickname();
		message = chat.getMessage();
		chatImageName = chat.getChatImageName();
		readOrNot = chat.isReadOrNot();
		sendingTime = chat.getSendingTime();
	}

}
