package com.youprice.onion.repository.chat;

import com.youprice.onion.entity.chat.Chat;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
	Slice<Chat> findByChatroomId(Long chatroomId, Pageable pageable);
}
