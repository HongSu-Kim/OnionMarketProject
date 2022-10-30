package com.youprice.onion.dto.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class ChatImageDto {

	private Long chatroomId;
	private Long memberId;
	private Long targetId;
	private String memberNickname;
	private MultipartFile chatImageName;
}
