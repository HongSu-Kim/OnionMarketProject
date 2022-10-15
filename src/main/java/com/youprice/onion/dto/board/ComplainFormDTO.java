package com.youprice.onion.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ComplainFormDTO {

    private Long memberId; // 회원번호 FK
    private Long productId; // 상품번호 FK
    private Long chatroomId; // 채팅방번호 FK
    private String complainType; // 신고유형
    private String complainContent; // 신고내용

}
