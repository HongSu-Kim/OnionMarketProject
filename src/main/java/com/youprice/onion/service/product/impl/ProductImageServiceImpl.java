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

    // imageName 생성
    @Override
    @Transactional(readOnly = true)
    public String getImageName() {

        LocalDateTime now = LocalDateTime.now();
        String imageName;

        do {
            imageName = now.format(DateTimeFormatter.BASIC_ISO_DATE).substring(2)
                    + now.format(DateTimeFormatter.ISO_LOCAL_TIME).replaceAll(":","").substring(0,6)
//                    + //고유값
            ;
        } while (productImageRepository.findByProductImageName(imageName).isPresent());

        return imageName;
    }
}
