package com.youprice.onion.dto.product;

import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductProgress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

    private Long productId;
    private Long memberId; //Member FK
    private Long townId; //Town FK
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

    public ProductDTO(Product product) {

        productId = product.getId();
        memberId = Long.valueOf(1); //product.getMember().getId();
        townId = Long.valueOf(1); //product.getTown().getId();
        productName = product.getProductName();
        subject = product.getSubject();
        content = product.getContent();
        price = product.getPrice();
        uploadDate = product.getUploadDate();
        updateDate = product.getUpdateDate();
        viewCount = product.getViewCount();
        productProgress = product.getProductProgress();
        payStatus = product.getPayStatus();
        blindStatus = product.getBlindStatus();

    }
}
