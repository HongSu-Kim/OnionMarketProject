package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.dto.product.ProductImageDTO;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductImage;
import com.youprice.onion.repository.product.ProductImageRepository;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    //상품 등록
    @Override
    public Long createProductDTO(ProductDTO productDTO, ProductImageDTO productImageDTO, MultipartFile file) {

        Product product = new Product();
        product.createProduct(productDTO);

        return productRepository.save(product).getId();
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

    //상품 하나에 대한 데이터
    public Product findOne(Long id) {
        return productManager.findOne(id);
    }

//    @Override
//    public ProductDTO getProductDTO(Long productId) {
//        return modelMapper.map(productRepository.findById(productId).orElse(null), ProductDTO.class);
//    }
}
