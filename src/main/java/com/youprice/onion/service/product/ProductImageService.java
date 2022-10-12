package com.youprice.onion.service.product;

import com.youprice.onion.entity.product.ProductImage;

import java.util.List;

public interface ProductImageService {
    List<ProductImage> findByProduct_ProductId(Long id);
}
