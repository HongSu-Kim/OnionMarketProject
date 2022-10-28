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

	@Override
	public List<ChatroomDTO> getChatroomDTOList(Long memberId) {
		return chatroomRepository.findAllByMemberId(memberId).stream().map(ChatroomDTO::new).collect(Collectors.toList());
	}

	@Override
	public ChatroomDTO getChatroomDTO(Long chatroomId, Pageable pageable) {

		return chatroomRepository.findById(chatroomId).map(chatroom -> {
			ChatroomDTO chatroomDTO = new ChatroomDTO(chatroom);

			Slice<ChatDTO> chatDTOSlice = chatRepository.findByChatroomId(chatroomId, pageable).map(ChatDTO::new);
			List<ChatDTO> chatDTOList = new ArrayList<>(chatDTOSlice.getContent());
			Collections.reverse(chatDTOList);
			chatroomDTO.setChatDTOSlice(new SliceImpl<>(chatDTOList, pageable, chatDTOSlice.hasNext()));

			return chatroomDTO;
		}).orElse(null);
	}

	@Override
	public void createChatroom(Long memberId, Long productId) {
		Member member = memberRepository.findById(memberId).orElse(null);
		Product product = productRepository.findById(productId).orElse(null);

		Chatroom chatroom = new Chatroom(member, product);
		chatroomRepository.save(chatroom);
	}
}
