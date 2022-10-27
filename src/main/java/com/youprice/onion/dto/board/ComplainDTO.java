package com.youprice.onion.dto.board;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.entity.board.Complain;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter @Setter
public class ComplainDTO {

    private Long complainId; // 신고번호 PK
    private Long memberId; // 회원번호 FK
    private Long productId; // 상품번호 FK
    private Long chatroomId; // 채팅방번호 FK
    private String complainType; // 신고유형
    private LocalDateTime complainDate; //신고일자
    private String complainContent; // 신고내용
    private String status; // 처리상태

    private MemberDTO memberDTO;
    private MemberDTO targetDTO;

    public ComplainDTO(Complain complain){
        this.complainId = complain.getId();
        this.memberId = complain.getMember().getId();
        if(complain.getProduct() == null){
            this.productId = 0L;
        } else {
            this.productId = complain.getProduct().getId();
        }
        if(complain.getChatroom() == null){
            this.chatroomId = 0L;
        } else {
            this.chatroomId = complain.getChatroom().getId();
        }
        this.complainType = complain.getComplainType();
        this.complainDate = complain.getComplainDate();
        this.complainContent = complain.getComplainContent();
        this.status = complain.getStatus();
        this.memberDTO = new MemberDTO(complain.getMember());
        this.targetDTO = new MemberDTO(complain.getProduct().getMember());
    }
}
