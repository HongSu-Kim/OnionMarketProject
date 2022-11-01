package com.youprice.onion.repository.order;

import com.youprice.onion.entity.order.Wish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

	// 각 상품의 찜 개수
	int countByProductId(Long productId);

	// 헤더 부분 각 회원의 찜 갯수
	int countByMemberId(Long memberId);

	// 찜 리스트
	@EntityGraph(attributePaths = {"product.member", "product.town.coordinate"})
	Page<Wish> findAllByMemberId(Long memberId, Pageable pageable);

	void deleteByMemberIdAndProductId(Long memberId, Long productId);

	boolean existsByMemberIdAndProductId(Long memberId, Long productId);
}
