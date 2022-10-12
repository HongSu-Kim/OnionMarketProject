package com.youprice.onion.dto.board;


import com.youprice.onion.entity.board.Inquiry;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class InquiryDTO {

    private Long inquiryId;
    private Long memberId;
    private String inquiryType; // 문의유형
    private String detailType; // 상세유형
    private String inquirySubject; // 문의제목
    private String inquiryContent; // 문의내용
    private LocalDate inquiryDate; // 문의등록일
    private String status; // 답변상태
    private boolean secret;

    public InquiryDTO(Inquiry inquiry) {
        this.inquiryId = inquiry.getId();
        this.memberId = inquiry.getMember().getId();
        this.inquiryType = inquiry.getInquiryType();
        this.detailType = inquiry.getDetailType();
        this.inquirySubject = inquiry.getInquirySubject();
        this.inquiryContent = inquiry.getInquiryContent();
        this.inquiryDate = inquiry.getInquiryDate();
        this.status = inquiry.getStatus();
        this.secret = inquiry.isSecret();
    }

}