package com.youprice.onion.service.chat.impl;

import com.youprice.onion.dto.chat.ChatDTO;
import com.youprice.onion.dto.chat.ChatImageDTO;
import com.youprice.onion.entity.chat.Chat;
import com.youprice.onion.entity.chat.Chatroom;
import com.youprice.onion.entity.member.Keyword;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.repository.chat.ChatRepository;
import com.youprice.onion.repository.chat.ChatroomRepository;
import com.youprice.onion.repository.member.KeywordRepositoy;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.service.chat.ChatService;
import com.youprice.onion.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ChatServiceImpl implements ChatService {

	private final ChatRepository chatRepository;
	private final ChatroomRepository chatroomRepository;
    private final MemberRepository memberRepository;
	private final ProductRepository productRepository;
	private final KeywordRepositoy keywordRepositoy;

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
	public int getAllNotRead(Long memberId) {
		return chatRepository.countAllNotRead(memberId);
	}
	
	// 채팅 읽음 
	@Override
	public void readChat(Long memberId, Long chatroomId) {
		chatRepository.readChat(memberId, chatroomId);
	}

	// 키워드 알림
	@Override
	public List<ChatDTO> alertChat(Long productId, String subject) {
		List<ChatDTO> chatDTOList = new ArrayList<>();

		// 키워드 알림보낼 대상
		List<Long> memberIdList = keywordRepositoy.findAllSearch(subject);

		for (Long memberId : memberIdList) {

			Member member = memberRepository.findById(memberId).orElse(null);
			Product product = productRepository.findById(0L).orElse(null);

			// 알림 채팅방이 없으면 생성
			Chatroom chatroom = chatroomRepository.findByMemberIdAndProductMemberId(memberId, 0L).orElseGet(() -> {
				Chatroom chatroom1 = new Chatroom(member, product);
				return chatroomRepository.save(chatroom1);
			});
			// 수정시간 갱신
			chatroom.setModifyDate(LocalDateTime.now());

			// 메세지 저장
			String message =
					"키워드 알림<br/>" +
					"<a href='/product/detail/" + productId + "'>" + subject + "</a>";
			Chat chat = new Chat(chatroom, product.getMember(), message, null);

			ChatDTO chatDTO = new ChatDTO(chatRepository.save(chat));
			chatDTO.setTargetId(memberId);

			chatDTOList.add(chatDTO);
		}
		
		return chatDTOList;
	}
}
