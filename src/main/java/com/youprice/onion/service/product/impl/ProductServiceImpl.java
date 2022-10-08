package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.dto.product.ProductImageDTO;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductImage;
import com.youprice.onion.repository.product.ProductImageRepository;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductRepository.ProductManager productManager;

    private final ProductImageServiceImpl productImageServiceImpl;
    //상품 등록
    @Override
    public void createProductDTO(ProductDTO productDTO, ProductImageDTO productImageDTO, MultipartFile file, int price ) {

        Product product = new Product();
        product.createProduct(productDTO);

        productRepository.save(product);
//상품 이미지 등록(예정)
//        ProductImage productImage = new ProductImage();
//        product = productManager.findByPrice(price);
//
//
//        productImageServiceImpl.createProductImageDTO(productImageDTO, file, product);

    }
    //전체 상품 조회
    @Override
    public List<Product> findAllProductDTO() {
        return productRepository.findAll();
    }

    //가격에 따른 데이터 조회(변경예정)
    public Product findByPrice(int price) {
        return productManager.findByPrice(price);
    }


}
