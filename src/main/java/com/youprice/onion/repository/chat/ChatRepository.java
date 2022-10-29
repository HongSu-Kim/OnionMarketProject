package com.youprice.onion.repository.chat;

import com.youprice.onion.entity.chat.Chat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
	Slice<Chat> findByChatroomId(Long chatroomId, Pageable pageable);

	@Query(value = "select c1.* from (select c.* from Chat c join Chatroom cr on c.chatroom_id = cr.chatroom_id where cr.chatroom_id = :chatroomId order by c.chat_id desc) c1 where rownum = 1", nativeQuery = true)
	Optional<Chat> findOneByChatroomId(@Param("chatroomId") Long chatroomId);


}
