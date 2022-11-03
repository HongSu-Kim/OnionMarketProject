package com.youprice.onion.repository.chat;

import com.youprice.onion.entity.chat.Chat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
	Slice<Chat> findByChatroomId(Long chatroomId, Pageable pageable);

	@Query(value = "select c0.* " +
					"from (" +
					"	select c.* " +
					"	from Chat c " +
					"	join Chatroom cr " +
					"		on c.chatroom_id = cr.chatroom_id " +
					"	where cr.chatroom_id = :chatroomId " +
					"	order by c.chat_id desc) c0 " +
					"where rownum = 1",
			nativeQuery = true)
	Optional<Chat> findOneByChatroomId(@Param("chatroomId") Long chatroomId);

	@Query("select count(c) " +
			"from Chat c " +
			"where c.chatroom.id = :chatroomId " +
			"and c.member.id <> :memberId " +
			"and c.read = false")
	int countNotRead(@Param("chatroomId") Long chatroomId, @Param("memberId")  Long memberId);

	@Modifying
	@Query("update Chat c " +
			"set c.read = true " +
			"where c.member.id <> :memberId " +
			"and c.chatroom.id = :chatroomId " +
			"and c.read = false")
	void readChat(@Param("memberId") Long memberId, @Param("chatroomId") Long chatroomId);
}
