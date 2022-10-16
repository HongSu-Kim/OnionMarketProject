package com.youprice.onion.repository.product;

import com.youprice.onion.entity.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long productId);

    @Modifying
    @Query("update Product p set p.viewCount = p.viewCount + 1 where p.id = ?1")
    int updateView(@RequestParam("productId") Long productId);
}
