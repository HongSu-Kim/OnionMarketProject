package com.youprice.onion.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NoticeUpdateDTO {

    private String noticeSubject; //공지제목
    private String noticeContent; //공지내용

    private List<NoticeImageDTO> noticeImageList;

    @Builder
    public NoticeUpdateDTO(String noticeSubject, String noticeContent){
        this.noticeSubject = noticeSubject;
        this.noticeContent = noticeContent;
        this.noticeImageList = getNoticeImageList();
    }

}
