package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.ProductImageDTO;
import com.youprice.onion.entity.product.ProductImage;

import java.util.List;

public interface ProductImageService {
    List<ProductImageDTO> getProductImage(Long productId);
    List<ProductImageDTO> getProductImageList();
}
