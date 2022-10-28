package com.youprice.onion.service.chat.impl;

import com.youprice.onion.dto.chat.ChatDTO;
import com.youprice.onion.entity.chat.Chat;
import com.youprice.onion.entity.chat.Chatroom;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.repository.chat.ChatRepository;
import com.youprice.onion.repository.chat.ChatroomRepository;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChatServiceImpl implements ChatService {

	private final ChatRepository chatRepository;
	private final ChatroomRepository chatroomRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

	@Override
	public void writeChat(ChatDTO chatDTO) {

		Member member = memberRepository.findById(chatDTO.getMemberId()).orElse(null);
		Chatroom chatroom = chatroomRepository.findById(chatDTO.getChatroomId()).orElse(null);

		Chat chat = new Chat(chatroom, member, chatDTO.getMessage(), chatDTO.getChatImageName());
		chatRepository.save(chat);
	}
}
