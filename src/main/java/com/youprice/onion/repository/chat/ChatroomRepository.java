package com.youprice.onion.repository.chat;

import com.youprice.onion.entity.chat.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
	
	// 각 상품의 채팅방 개수
	int countByProductId(Long productId);
}
