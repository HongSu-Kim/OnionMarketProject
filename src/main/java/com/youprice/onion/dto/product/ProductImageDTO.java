package com.youprice.onion.dto.product;

import com.youprice.onion.entity.product.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImageDTO {

    private Long id;
    private Product product; //상품번호 FK
    private String productImageName; //이미지파일이름
}
