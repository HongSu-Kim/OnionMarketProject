package com.youprice.onion.dto.chat;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.entity.chat.Chatroom;
import com.youprice.onion.entity.chat.ChatroomState;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatroomDTO {

	private Long chatroomId;//채팅방번호 PK
	private ChatroomState state; //채팅의 진행 상태, 진행중 - 종료 - 삭제(DB삭제 X)
	private LocalDateTime createDate; //생성시간
	private LocalDateTime modifyDate; //수정시간(마지막 채팅 시간)

	private Slice<ChatDTO> chatDTOSlice;
	private MemberDTO memberDTO;
	private ProductDTO productDTO;

	public ChatroomDTO(Chatroom chatroom) {
		chatroomId = chatroom.getId();
		state = chatroom.getState();
		createDate = chatroom.getCreateDate();
		modifyDate = chatroom.getModifyDate();

		memberDTO = new MemberDTO(chatroom.getMember());
		productDTO = new ProductDTO(chatroom.getProduct());
	}
}