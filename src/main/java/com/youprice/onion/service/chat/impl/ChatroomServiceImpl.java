package com.youprice.onion.service.chat.impl;

import com.youprice.onion.dto.chat.ChatDTO;
import com.youprice.onion.dto.chat.ChatroomDTO;
import com.youprice.onion.entity.chat.Chatroom;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.repository.chat.ChatRepository;
import com.youprice.onion.repository.chat.ChatroomRepository;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.service.chat.ChatroomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChatroomServiceImpl implements ChatroomService {

	private final ChatroomRepository chatroomRepository;
	private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

	// 채팅방 리스트
	@Override
	public List<ChatroomDTO> getChatroomDTOList(Long memberId) {
		return chatroomRepository.findAllByMemberIdOrderByModifyDate(memberId).stream().map(chatroom -> {
			ChatroomDTO chatroomDTO = new ChatroomDTO(chatroom);

			chatRepository.findOneByChatroomId(chatroomDTO.getChatroomId()).map(chat -> {
				chatroomDTO.setChatDTO(new ChatDTO(chat));
				return chat;
			}).orElse(null);

			return chatroomDTO;
		}).collect(Collectors.toList());
	}

	// 채팅방 
	@Override
	public ChatroomDTO getChatroomDTO(Long chatroomId, Pageable pageable) {
		return chatroomRepository.findById(chatroomId).map(chatroom -> {
			ChatroomDTO chatroomDTO = new ChatroomDTO(chatroom);

			Slice<ChatDTO> chatDTOSlice = chatRepository.findByChatroomId(chatroomId, pageable).map(ChatDTO::new);
			List<ChatDTO> chatDTOList = new ArrayList<>(chatDTOSlice.getContent());
			Collections.reverse(chatDTOList);

			chatroomDTO.setSlice(new SliceImpl<>(chatDTOList, chatDTOSlice.getPageable(), chatDTOSlice.hasNext()));
			return chatroomDTO;
		}).orElse(null);
	}

	// 채팅방 생성
	@Override
	public Long createChatroom(Long memberId, Long productId) {
		// 채팅방이 이미 존재하면 아이디 반환
		Chatroom chatroom = chatroomRepository.findByMemberIdAndProductId(memberId, productId).orElse(null);
		if (chatroom != null) {
			return chatroom.getId();
		}

		Member member = memberRepository.findById(memberId).orElse(null);
		Product product = productRepository.findById(productId).orElse(null);

		chatroom = new Chatroom(member, product);
		return chatroomRepository.save(chatroom).getId();
	}
}
