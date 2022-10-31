package com.youprice.onion.dto.board;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter@Setter
public class ComplainFormDTO {

    private Long memberId; // 회원번호 FK
    private Long productId; // 상품번호 FK
    @NotEmpty(message = "유형을 선택해주세요.")
    private String complainType; // 신고유형
    @NotEmpty(message = "내용을 작성해주세요.")
    private String complainContent; // 신고내용
}
