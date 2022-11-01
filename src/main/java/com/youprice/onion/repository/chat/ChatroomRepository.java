package com.youprice.onion.repository.chat;

import com.youprice.onion.entity.chat.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
	
	// 각 상품의 채팅방 개수
	int countByProductId(Long productId);

	// 채팅방 리스트
	@Query("select cr " +
			"from Chatroom cr " +
			"join fetch cr.member m1 " +
			"join fetch cr.product p " +
			"join fetch p.member m2 " +
			"where :memberId in (m1.id, m2.id) " +
			"order by cr.modifyDate desc")
	List<Chatroom> findAllByMemberIdOrderByModifyDate(@Param("memberId") Long memberId);

	// 채팅방 - 채팅리스트
	Optional<Chatroom> findByMemberIdAndProductId(Long memberId, Long id);
}
