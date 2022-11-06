package com.youprice.onion.repository.product;

import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductProgress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

//@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQueryDsl {
    //상품번호로 상품 하나 조회
    @Override
    @EntityGraph(attributePaths = {"productImageList"})
    Optional<Product> findById(Long productId);

    //상품번호로 조회 후 상품의 조회수 증가
    @Modifying
    @Query("update Product p set p.viewCount = p.viewCount + 1 where p.id = ?1")
    int updateView(@RequestParam("productId") Long productId);

    //개인 판매 상품 리스트
    Page<Product> findAllByMemberIdAndProductProgressIn(Long memberId, ProductProgress[] productProgressList, Pageable pageable);

    // 하위 카테고리 상품 리스트
    List<Product> findByCategoryId(Long categoryId);

    //
    List<Product> findByCategoryIdBetween(Long start, Long end);

    //제목과 내용으로 검색한 리스트 조회
    List<Product> findBySubjectContainingOrContentContaining(String subject, String content);

    boolean existsBySubject(String subject);

	@Modifying
	@Query("update Product p set p.blindStatus = true where p.auctionDeadline < current_timestamp")
	void actionBlind();
}
