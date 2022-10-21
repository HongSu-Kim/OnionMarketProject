package com.youprice.onion.repository.product;

import com.youprice.onion.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    //상품번호로 상품 하나 조회
    @Override
    @EntityGraph(attributePaths = {"productImageList"})
    Optional<Product> findById(Long productId);

    //상품번호로 조회 후 상품의 조회수 증가
    @Modifying
    @Query("update Product p set p.viewCount = p.viewCount + 1 where p.id = ?1")
    int updateView(@RequestParam("productId") Long productId);

	// 판매 상품 리스트
	Page<Product> findByMemberId(Long memberId, Pageable pageable);

    //
    List<Product> findByCategoryIdBetween(Long start, Long end);
    //
    List<Product> findBySubjectContainingOrContentContaining(String subject,String content);


}
