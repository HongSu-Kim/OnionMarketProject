package com.youprice.onion.controller.chat;

import com.youprice.onion.dto.chat.ChatDTO;
import com.youprice.onion.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("chat")
@Slf4j
public class ChatController {

	private final SimpMessagingTemplate template;
	private final ChatService chatService;

	@MessageMapping("/chat/enter")
	public void enter(ChatDTO chatDTO){
		log.error("ChatController : /chat/enter : " + chatDTO.getMemberId());
		chatDTO.setMessage(chatDTO.getMemberId() + "님이 채팅방에 참여하였습니다.");
		template.convertAndSend("/sub/chatroom/room/" + chatDTO.getChatroomId(), chatDTO);
	}

	@MessageMapping("/chat/message")
	public void message(ChatDTO chatDTO){
		log.error("ChatController : /chat/message : " + chatDTO.getMessage());
		chatService.writeChat(chatDTO);
		template.convertAndSend("/sub/chatroom/room/" + chatDTO.getChatroomId(), chatDTO);
	}

}
