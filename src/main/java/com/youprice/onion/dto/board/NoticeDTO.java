package com.youprice.onion.dto.board;

import com.youprice.onion.entity.board.NoticeType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class NoticeDTO {
    @NotEmpty(message = "공지의 타입을 지정해주세요.")
    private NoticeType noticeType; //공지타입(notice, qna, event ...)

    @NotEmpty(message = "제목이 없습니다.")
    private String noticeSubject; //공지제목

    @NotEmpty(message = "공지에 내용이 없습니다.")
    private String noticeContent; //공지내용

}
