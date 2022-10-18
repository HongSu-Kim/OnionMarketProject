package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.ProductImageDTO;
import com.youprice.onion.entity.product.ProductImage;

import java.util.List;

public interface ProductImageService {
    //상품번호로 이미지 리스트 조회
    List<ProductImageDTO> getProductImage(Long productId);
    //이미지 고유번호
    String getImageName();
    //이미지 전체 조회
    List<ProductImageDTO> getProductImageList();

}
