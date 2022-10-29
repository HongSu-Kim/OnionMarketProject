package com.youprice.onion.repository.chat;

import com.youprice.onion.entity.chat.Chatroom;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
	
	// 각 상품의 채팅방 개수
	int countByProductId(Long productId);

//	@EntityGraph(attributePaths = { "member", "product.member" })
	//
	@Query(value = "select cr from Chatroom cr left join fetch cr.member m1 left join fetch cr.product p join fetch p.member m2 where ?1 in (m1.id, m2.id) order by cr.modifyDate desc")
	List<Chatroom> findAllByMemberIdOrderByModifyDate(Long memberId);

	@Override
	@EntityGraph(attributePaths = { "member", "product" })
	Optional<Chatroom> findById(Long chatroomId);

	Optional<Chatroom> findByMemberIdAndProductId(Long memberId, Long id);
}
