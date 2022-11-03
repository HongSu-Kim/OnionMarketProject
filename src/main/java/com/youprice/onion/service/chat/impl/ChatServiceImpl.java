package com.youprice.onion.service.chat.impl;

import com.youprice.onion.dto.chat.ChatDTO;
import com.youprice.onion.dto.chat.ChatImageDTO;
import com.youprice.onion.entity.chat.Chat;
import com.youprice.onion.entity.chat.Chatroom;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.repository.chat.ChatRepository;
import com.youprice.onion.repository.chat.ChatroomRepository;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.service.chat.ChatService;
import com.youprice.onion.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChatServiceImpl implements ChatService {

	private final ChatRepository chatRepository;
	private final ChatroomRepository chatroomRepository;
    private final MemberRepository memberRepository;

	// 메세지 저장
	@Override
	public ChatDTO writeChat(ChatDTO chatDTO) {

		Member member = memberRepository.findById(chatDTO.getMemberId()).orElse(null);
		Chatroom chatroom = chatroomRepository.findById(chatDTO.getChatroomId()).orElse(null);

		// 채팅방 수정일 갱신
		chatroom.setModifyDate(LocalDateTime.now());

		// 메세지 저장
		Chat chat = new Chat(chatroom, member, chatDTO.getMessage(), null);
		return new ChatDTO(chatRepository.save(chat));
	}

	// 이미지 저장
	@Override
	public ChatDTO uploadImage(ChatImageDTO chatImageDto) throws IOException {

		Member member = memberRepository.findById(chatImageDto.getMemberId()).orElse(null);
		Chatroom chatroom = chatroomRepository.findById(chatImageDto.getChatroomId()).orElse(null);

		// 채팅방 수정일 갱신
		chatroom.setModifyDate(LocalDateTime.now());

		// 이미지 저장(파일)
		String chatImageName = ImageUtil.store(chatImageDto.getChatImageName(), "chat");

		// 이미지 저장(DB)
		Chat chat = new Chat(chatroom, member, null, chatImageName);
		ChatDTO chatDTO = new ChatDTO(chatRepository.save(chat));
		
		return chatDTO;
	}

	@Override
	public void readChat(Long memberId, Long chatroomId) {
		chatRepository.readChat(memberId, chatroomId);
	}
}
