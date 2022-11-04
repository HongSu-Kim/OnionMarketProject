package com.youprice.onion.dto.board;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.product.ProductFindDTO;
import com.youprice.onion.entity.board.Complain;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter @Setter
public class ComplainDTO {

    private Long complainId; // 신고번호 PK
    private Long memberId; // 회원번호 FK
    private Long productId; // 상품번호 FK
    private String complainType; // 신고유형
    private LocalDate complainDate; //신고일자
    private String complainContent; // 신고내용
    private String status; // 처리상태

    private MemberDTO memberDTO;
    private MemberDTO targetDTO;
    private ProductFindDTO productFindDTO;

    public ComplainDTO(Complain complain){
        this.complainId = complain.getId();
        this.memberId = complain.getMember().getId();
        this.productId = complain.getProduct().getId();
        this.complainType = complain.getComplainType();
        this.complainDate = complain.getComplainDate();
        this.complainContent = complain.getComplainContent();
        this.status = complain.getStatus();
        this.memberDTO = new MemberDTO(complain.getMember());
        this.targetDTO = new MemberDTO(complain.getProduct().getMember());
        this.productFindDTO = new ProductFindDTO(complain.getProduct());
    }
}
