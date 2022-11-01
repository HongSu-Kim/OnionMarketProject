package com.youprice.onion.entity.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChatroomState {
	//채팅방 상태 - chatting,end,delete

	CHATTING("채팅중"),//채팅중
	END("종료"),//종료
	DELETE("삭제");//삭제

	private String kor;

}
