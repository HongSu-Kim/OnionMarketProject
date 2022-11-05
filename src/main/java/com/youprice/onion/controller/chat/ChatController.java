package com.youprice.onion.controller.chat;

import com.youprice.onion.dto.chat.ChatDTO;
import com.youprice.onion.dto.chat.ChatImageDTO;
import com.youprice.onion.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("chat")
@Slf4j
public class ChatController {

	private final SimpMessagingTemplate template;
	private final ChatService chatService;

	// 메세지 전송
	@MessageMapping("/chat/message")
	public void message(ChatDTO chatDTO) {
		Long targetId = chatDTO.getTargetId();

		// 메세지 저장
		chatDTO = chatService.writeChat(chatDTO);

		// 메세지 전송
		template.convertAndSend("/sub/chat/" + chatDTO.getMemberId(), chatDTO);
		template.convertAndSend("/sub/chat/" + targetId, chatDTO);
	}

	// 이미지 전송
	@PostMapping("/image")
	@ResponseBody
	public ResponseEntity<?> chatImage(ChatImageDTO chatImageDTO) {
		try {
			// 이미지 저장
			ChatDTO chatDTO = chatService.uploadImage(chatImageDTO);

			// 이미지 전송
			template.convertAndSend("/sub/chat/" + chatImageDTO.getMemberId(), chatDTO);
			template.convertAndSend("/sub/chat/" + chatImageDTO.getTargetId(), chatDTO);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("채팅 이미지 저장 에러 :" + e.toString());
			return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	// 채팅 읽음
	@PutMapping("readChat")
	public void readChat(Long memberId, Long chatroomId) {
		chatService.readChat(memberId, chatroomId);
	}

}
