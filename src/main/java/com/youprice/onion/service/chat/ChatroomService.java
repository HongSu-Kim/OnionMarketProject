package com.youprice.onion.service.chat;

import com.youprice.onion.dto.chat.ChatroomDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChatroomService {

	public List<ChatroomDTO> getChatroomDTOList(Long memberId);
	public ChatroomDTO getChatroomDTO(Long chatroomId, Pageable pageable);
	public Long createChatroom(Long memberId, Long productId);

}
