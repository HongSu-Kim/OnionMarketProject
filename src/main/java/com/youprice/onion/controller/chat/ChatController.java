package com.youprice.onion.controller.chat;

import com.youprice.onion.dto.chat.ChatDTO;
import com.youprice.onion.dto.chat.ChatImageDto;
import com.youprice.onion.repository.chat.ChatRepository;
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
	private final ChatRepository chatRepository;

	// 채팅 메세지
	@MessageMapping("/chat/message")
	public void message(ChatDTO chatDTO) {
		// 채팅 메세지 저장
		chatDTO.setSendingTime(LocalDateTime.now());
		chatService.writeChat(chatDTO);

		// 채팅 메세지 전송
		template.convertAndSend("/sub/chat/" + chatDTO.getMemberId(), chatDTO);
		template.convertAndSend("/sub/chat/" + chatDTO.getTargetId(), chatDTO);
	}

	// 채팅 이미지
	@MessageMapping("/chat/image")
	public void image(ChatDTO chatDTO) {
		// 채팅 이미지 전송
		template.convertAndSend("/sub/chat/" + chatDTO.getMemberId(), chatDTO);
		template.convertAndSend("/sub/chat/" + chatDTO.getTargetId(), chatDTO);
	}

	@PostMapping("/image")
	@ResponseBody
	public ResponseEntity<?> chatImage(ChatImageDto chatImageDto) {
		try {
			// 채팅 이미지 저장
			ChatDTO chatDTO = chatService.uploadImage(chatImageDto);
			return new ResponseEntity<>(chatDTO, HttpStatus.OK);
		} catch (Exception e) {
			log.error("채팅 이미지 저장 에러 :" + e.toString());
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

}
