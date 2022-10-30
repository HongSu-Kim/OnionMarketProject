package com.youprice.onion.controller.chat;

import com.youprice.onion.dto.chat.ChatDTO;
import com.youprice.onion.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("chat")
@Slf4j
public class ChatController {

	private final SimpMessagingTemplate template;
	private final ChatService chatService;

	// 채팅 메세지
	@MessageMapping("/chat/message")
	public void message(ChatDTO chatDTO){
		log.error("ChatController : /chat/message : " + chatDTO.getMessage());
		chatDTO.setSendingTime(LocalDateTime.now());
		chatService.writeChat(chatDTO);
		template.convertAndSend("/sub/chat/" + chatDTO.getMemberId(), chatDTO);
		template.convertAndSend("/sub/chat/" + chatDTO.getTargetId(), chatDTO);
	}

}
