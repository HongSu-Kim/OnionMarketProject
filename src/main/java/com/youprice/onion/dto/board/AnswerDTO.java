package com.youprice.onion.dto.board;

import com.youprice.onion.entity.board.Answer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class AnswerDTO {

    private Long answerId; // 답변번호 PK
    private Long inquiryId; // 문의글번호 FK
    private Long memberId; // 회원번호 FK
    private String answerContent; // 답변내용
    private LocalDateTime answerDate; // 답변등록일

    public AnswerDTO(Answer answer) {
        this.answerId = answer.getId();
        this.inquiryId = answer.getInquiry().getId();
        this.memberId = answer.getMember().getId();
        this.answerContent = answer.getAnswerContent();
        this.answerDate = answer.getAnswerDate();
    }
}
