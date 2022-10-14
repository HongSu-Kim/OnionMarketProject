package com.youprice.onion.dto.board;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class AnswerFormDTO {

    private Long inquiryId; // 문의글번호 FK
    private Long memberId; // 회원번호 FK
    @NotEmpty(message = "답변내용을 입력해주세요")
    private String answerContent; // 답변내용
}
