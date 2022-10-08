package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.product.ProductImageDTO;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductImage;
import com.youprice.onion.repository.product.ProductImageRepository;
import com.youprice.onion.service.product.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final ProductImageRepository productImageRepository;
    private final ProductImageRepository.ProductImageManager productImageManager;

    public void createProductImageDTO(ProductImageDTO productImageDTO, MultipartFile file, Product product) throws  Exception{

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        ProductImage productImage = new ProductImage();

        productImage.createProductImage(productImageDTO,product);

        productImageRepository.save(productImage);
    }

    public List<ProductImage> findByProduct_ProductId(Long id){
        return productImageManager.findByProduct_ProductId(id);
    }

}
