package com.youprice.onion.repository.chat;

import com.youprice.onion.entity.chat.Chatroom;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
	
	// 각 상품의 채팅방 개수
	int countByProductId(Long productId);

	@EntityGraph(attributePaths = { "member", "product.member" })
	List<Chatroom> findAllByMemberId(Long memberId);

	@Override
	@EntityGraph(attributePaths = { "member", "product" })
	Optional<Chatroom> findById(Long chatroomId);

	Optional<Chatroom> findByMemberIdAndProductId(Long memberId, Long id);
}
