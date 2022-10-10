package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.ProductImageDTO;
import com.youprice.onion.entity.product.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductImageService {

    void createProductImageDTO(ProductImageDTO productImageDTO, MultipartFile file, Long id)throws Exception;

    List<ProductImage> findByProduct_ProductId(Long id);
}
