package com.youprice.onion.dto.board;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class NoticeUpdateDTO {
    @NotEmpty(message = "유형을 선택해주세요")
    private String noticeType;
    @NotEmpty(message = "제목을 입력해주세요")
    private String noticeSubject;
    @NotEmpty(message = "내용을 작성해주세요")
    private String noticeContent;
    private List<MultipartFile> noticeImageName;
}
