package com.youprice.onion.repository.product;

import com.youprice.onion.entity.product.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    //상품번호로 이미지 전체조회
    List<ProductImage> findAllByProductId(Long productId);
    //상품번호로 하나의 이미지 조회
    List<ProductImage> findByProductId(Long ID);
    //imageName 중복확인
    Optional<ProductImage> findByProductImageName(String productImageName);
}
