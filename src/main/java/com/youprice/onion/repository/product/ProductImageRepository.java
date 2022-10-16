package com.youprice.onion.repository.product;

import com.youprice.onion.entity.product.ProductImage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    //이미지로 상품 조회
    List<ProductImage> findAllByProductId(Long productId);
    List<ProductImage> findByProductId(Long ID);

}
