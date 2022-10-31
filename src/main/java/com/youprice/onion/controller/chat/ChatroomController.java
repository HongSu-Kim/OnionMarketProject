package com.youprice.onion.controller.chat;

import com.youprice.onion.dto.chat.ChatroomDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.chat.ChatroomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("chatroom")
@Slf4j
public class ChatroomController {

	private final ChatroomService chatroomService;

	// 전체 채팅방 조회
	@GetMapping("list")
	@ResponseBody
	public ResponseEntity<?> chatroomList(@LoginUser SessionDTO sessionDTO) {
		if (sessionDTO == null) return new ResponseEntity<>("/member/login", HttpStatus.UNAUTHORIZED);

		List<ChatroomDTO> chatroomDTOList = chatroomService.getChatroomDTOList(sessionDTO.getId());

		return new ResponseEntity<>(chatroomDTOList, HttpStatus.OK);
	}

	// 채팅방 생성
	@PostMapping("create")
	@ResponseBody
	public ResponseEntity<?> chatroomCreate(@LoginUser SessionDTO sessionDTO, @RequestParam Long productId) {
		if (sessionDTO == null) return new ResponseEntity<>("/member/login", HttpStatus.UNAUTHORIZED);

		Long chatroomId = chatroomService.createChatroom(sessionDTO.getId(), productId);
		return new ResponseEntity<>(chatroomId, HttpStatus.OK);
	}

	// 채팅 내역
	@GetMapping("room/{chatroomId}")
	@ResponseBody
	public ResponseEntity<?> chatroomRoom(@PathVariable Long chatroomId,
										  @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

		ChatroomDTO chatroomDTO = chatroomService.getChatroomDTO(chatroomId, pageable);

		return new ResponseEntity<>(chatroomDTO, HttpStatus.OK);
	}

}
