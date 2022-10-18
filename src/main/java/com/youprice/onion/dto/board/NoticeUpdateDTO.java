package com.youprice.onion.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class NoticeUpdateDTO {

    private String noticeSubject; //공지제목
    private String noticeContent; //공지내용

    private List<MultipartFile> noticeImageName;



}
