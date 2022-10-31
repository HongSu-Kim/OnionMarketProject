package com.youprice.onion.dto.board;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class NoticeFormDTO {

    private Long memberId;
    @NotEmpty(message = "유형을 선택해주세요")
    private String noticeType;
    @NotEmpty(message = "공지제목을 입력해주세요")
    private String noticeSubject;
    @NotEmpty(message = "공지내용을 작성해주세요")
    private String noticeContent;
}
