package com.youprice.onion.service.chat.impl;

import com.youprice.onion.repository.chat.ChatRepository;
import com.youprice.onion.repository.chat.ChatroomRepository;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ChatRepository chatRepository;
    private final ChatroomRepository chatroomRepository;


}
