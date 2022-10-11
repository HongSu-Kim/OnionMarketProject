package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.product.ProductImageDTO;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductImage;
import com.youprice.onion.repository.product.ProductImageRepository;
import com.youprice.onion.service.product.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductImageRepository.ProductImageManager productImageManager;

    @Override
    public List<ProductImage> findByProduct_ProductId(Long id){
        return productImageManager.findByProduct_ProductId(id);
    }

}
