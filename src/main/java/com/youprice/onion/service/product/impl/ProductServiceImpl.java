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
    public Long addProduct(ProductDTO productDTO, ProductImageDTO productImageDTO, MultipartFile file)throws  Exception {

        Product product = new Product();
        ProductImage productImage = new ProductImage();

        product.createProduct(productDTO);

        productRepository.save(product);

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        String productImageName = file.getOriginalFilename();

        File saveFile = new File(projectPath, productImageName);

        file.transferTo(saveFile);
        product = productRepository.findById(product.getId()).orElse(null);

        productImage.addProductImage(productImageDTO,product,productImageName);

        productImageRepository.save(productImage);
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

    //상품 삭제
    @Override
    @Transactional
    public void deleteProduct(Long productId, Long productImageId, MultipartFile file) throws Exception {

        File deleteFile = new File(String.valueOf(file));
        deleteFile.delete();
        productImageRepository.deleteById(productImageId);
        productRepository.deleteById(productId);
    }
}
