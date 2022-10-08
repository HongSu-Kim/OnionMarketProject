package com.youprice.onion.dto.product;

import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.ProductProgress;
import com.youprice.onion.entity.product.Town;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDTO {

    private Long id;
    private Member member; //Member FK
    private Town town; //Town FK
    private String productName; //상품명
    private String subject; //제목
    private String content; //내용
    private int price; //상품가격
    private LocalDateTime uploadDate; //등록시간
    private LocalDateTime updateDate; //수정일
    private int viewCount; //조회수
    private ProductProgress productProgress; //판매상태 Reserved,tradings,soldout 예약중,거래중,판매완료
    private String payStatus; //페이현황
    private String blindStatus; //블라인드현황


}
