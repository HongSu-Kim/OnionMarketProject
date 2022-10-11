package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.dto.product.ProductImageDTO;
import com.youprice.onion.entity.product.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Long createProductDTO(ProductDTO productDTO) throws  Exception;
    List<Product> findAllProductDTO();
    Optional<Product> findById(Long id);
}
