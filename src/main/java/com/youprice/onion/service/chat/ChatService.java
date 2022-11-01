package com.youprice.onion.service.chat;

import com.youprice.onion.dto.chat.ChatDTO;
import com.youprice.onion.dto.chat.ChatImageDto;

import java.io.IOException;

public interface ChatService {

	void writeChat(ChatDTO chatDTO);
	ChatDTO uploadImage(ChatImageDto chatImageDto) throws IOException;
}
