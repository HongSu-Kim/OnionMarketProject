package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.product.ProductImageDTO;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductImage;
import com.youprice.onion.repository.product.ProductImageRepository;
import com.youprice.onion.service.product.ProductImageService;
import com.youprice.onion.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    ProductService productService;
    private final ProductImageRepository productImageRepository;
    private final ProductImageRepository.ProductImageManager productImageManager;

    @Override
    @Transactional
    public void createProductImageDTO(ProductImageDTO productImageDTO, MultipartFile file, Long id) throws  Exception{

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        ProductImage productImage = new ProductImage();

        Product product = productService.findOne(id);

        productImage.createProductImage(productImageDTO,product,fileName);

        productImageRepository.save(productImage);
    }

    @Override
    public List<ProductImage> findByProduct_ProductId(Long id){
        return productImageManager.findByProduct_ProductId(id);
    }

}
