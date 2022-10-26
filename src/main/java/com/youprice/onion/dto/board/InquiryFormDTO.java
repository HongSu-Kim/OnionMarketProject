package com.youprice.onion.dto.board;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class InquiryFormDTO {

    private Long memberId;

    @NotEmpty(message = "문의유형을 선택해주세요")
    private String inquiryType; // 문의유형

    @NotEmpty(message = "문의유형을 선택해주세요")
    private String detailType; // 상세유형

    @NotEmpty(message = "제목을 입력해주세요")
    @Size(max = 200)
    private String inquirySubject; // 문의제목

    @NotEmpty(message = "문의 내용을 작성해주세요")
    private String inquiryContent; // 문의내용
    private String status;
    private boolean secret;

}