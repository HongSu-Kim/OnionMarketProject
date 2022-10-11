package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.dto.product.ProductImageDTO;
import com.youprice.onion.entity.product.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Long addProduct(ProductDTO productDTO) throws  Exception;
    List<ProductDTO> getProductList();
    Optional<Product> findById(Long id);

    void addProductImage(ProductImageDTO productImageDTO, MultipartFile file, Long id)throws Exception;

}
