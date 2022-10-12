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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@ToString
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductRepository.ProductManager productManager;
    private final ProductImageRepository productImageRepository;

    //상품 등록
    @Override
    @Transactional
    public Long addProduct(ProductDTO productDTO) {

        Product product = new Product();
        product.createProduct(productDTO);

        return productRepository.save(product).getId();
    }
    //전체 상품 조회
    @Override
    public List<ProductDTO> getProductList() {
        return productRepository.findAll().stream()
                .map(product -> new ProductDTO(product))
                .collect(Collectors.toList());
    }

    //상품 하나에 대한 데이터
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    @Transactional
    public void addProductImage(ProductImageDTO productImageDTO, MultipartFile file, Long id) throws  Exception{

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

//        UUID uuid = UUID.randomUUID();
//        uuid + "_" +
        String productImageName = file.getOriginalFilename();

        File saveFile = new File(projectPath, productImageName);

        file.transferTo(saveFile);

        ProductImage productImage = new ProductImage();

        Product product = productRepository.findById(id).orElse(null);

        productImage.addProductImage(productImageDTO,product,productImageName);

        productImageRepository.save(productImage);
    }
}
