package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.dto.product.ProductImageDTO;
import com.youprice.onion.entity.product.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    void createProductDTO(ProductDTO productDTO, ProductImageDTO productImageDTO, MultipartFile file, int price );
    List<Product> findAllProductDTO();
}
