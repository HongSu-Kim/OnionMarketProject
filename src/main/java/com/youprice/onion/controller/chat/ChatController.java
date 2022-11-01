package com.youprice.onion.controller.chat;

import com.youprice.onion.dto.chat.ChatDTO;
import com.youprice.onion.dto.chat.ChatImageDto;
import com.youprice.onion.service.chat.ChatService;
import com.youprice.onion.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public void message(ChatDTO chatDTO) {
		log.error("ChatController : /chat/message : " + chatDTO.getMessage());
		chatDTO.setSendingTime(LocalDateTime.now());
		chatService.writeChat(chatDTO);
		template.convertAndSend("/sub/chat/" + chatDTO.getMemberId(), chatDTO);
		template.convertAndSend("/sub/chat/" + chatDTO.getTargetId(), chatDTO);
	}

	// 채팅 이미지
	@MessageMapping("/chat/image")
	public void image(ChatDTO chatDTO) {
		log.error("ChatController : /chat/image : " + chatDTO.getChatImageName());

		if (!ImageUtil.existsImage(chatDTO.getChatImageName(), "chat")) {
			chatDTO.setChatImageName(null);
		}

		template.convertAndSend("/sub/chat/" + chatDTO.getMemberId(), chatDTO);
		template.convertAndSend("/sub/chat/" + chatDTO.getTargetId(), chatDTO);
	}

	@PostMapping("/image")
	@ResponseBody
	public ResponseEntity<?> chatImage(ChatImageDto chatImageDto) {
		try {
			ChatDTO chatDTO = chatService.uploadImage(chatImageDto);
			return new ResponseEntity<>(chatDTO, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

}
