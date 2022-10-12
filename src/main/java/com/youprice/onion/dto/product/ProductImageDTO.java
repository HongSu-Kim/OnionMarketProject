package com.youprice.onion.dto.product;

import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductImageDTO {

    private Long productImageId;
    private Long productId; //상품번호 FK
    private String productImageName; //이미지파일이름

    public ProductImageDTO(ProductImage productImage) {

        productImageId = productImage.getId();
        productId = productImage.getProduct().getId();
        productImageName = productImage.getProductImageName();

    }
}
