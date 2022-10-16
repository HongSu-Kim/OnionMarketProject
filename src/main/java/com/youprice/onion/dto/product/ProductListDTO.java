package com.youprice.onion.dto.product;

import com.youprice.onion.entity.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductListDTO {

    private Long productId; //
    private String subject; //제목
    private int price; //상품가격
    private LocalDateTime uploadDate; //등록시간
    private LocalDateTime updateDate; //수정일

    private String productImageName; //상품 이미지

    public ProductListDTO(Product product) {

        this.productId = product.getId();
        this.subject = product.getSubject();
        this.price = product.getPrice();
        if(uploadDate==null) {
            this.uploadDate = LocalDateTime.now();
        }
        if(uploadDate!=null) {
            this.updateDate = LocalDateTime.now();
        }
        this.productImageName = product.getProductImageList().get(0).getProductImageName();
    }

}

