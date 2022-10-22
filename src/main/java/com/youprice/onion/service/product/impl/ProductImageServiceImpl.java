package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.product.ProductImageDTO;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductImage;
import com.youprice.onion.repository.product.ProductImageRepository;
import com.youprice.onion.service.product.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;

    @Override
    public List<ProductImageDTO> getProductImageList(){
        return productImageRepository.findAll()
                .stream().map(productImage -> new ProductImageDTO(productImage))
                .collect(Collectors.toList());
    }
    @Override
    public List<ProductImageDTO> getProductImage(Long productId) {
        return productImageRepository.findAllByProductId(productId)
                .stream().map(ProductImageDTO::new)
                .collect(Collectors.toList());
    }
    public void deleteImage(Long imageId){
        ProductImage productImage = productImageRepository.findById(imageId).orElse(null);
        productImageRepository.delete(productImage);
    }

}
