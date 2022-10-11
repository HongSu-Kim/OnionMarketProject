package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.dto.product.ProductImageDTO;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductImage;
import com.youprice.onion.repository.product.ProductImageRepository;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@ToString
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductRepository.ProductManager productManager;

    //상품 등록
    @Override
    @Transactional()
    public Long createProductDTO(ProductDTO productDTO)throws Exception {

        Product product = new Product();
        product.createProduct(productDTO);

        return productRepository.save(product).getId();
    }
    //전체 상품 조회
    @Override
    public List<Product> findAllProductDTO() {
        return productRepository.findAll();
    }

    //상품 하나에 대한 데이터
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

}
