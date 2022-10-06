package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.entity.product.Product;
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
    //상품 등록
    public void createProductDTO(ProductDTO productDTO, MultipartFile file) throws Exception{
        String imagePath = System.getProperty("user.dir") + "\\src\\main\\resources\\image\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(imagePath, fileName);
        file.transferTo(saveFile);

        Product product = new Product();
        product.createProduct(productDTO);

        productRepository.save(product);
    }
    //전체 상품 조회
    public List<Product> findAllProductDTO() {
        return productManager.findAll();
    }

    //가격에 따른 데이터 조회(변경예정)
    public List<Product> findByPrice(Long id) {
        return productManager.findByPrice(id);
    }
}
