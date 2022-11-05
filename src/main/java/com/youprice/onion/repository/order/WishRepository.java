package com.youprice.onion.repository.order;

import com.youprice.onion.entity.order.Wish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

	// 각 상품의 찜 개수
	int countByProductId(Long productId);

	// 각 회원의 찜 개수
	int countByMemberId(Long memberId);

	// 찜 리스트
//	@EntityGraph(attributePaths = {"product.member", "product.town.coordinate"})
	@Query("select w " +
			"from Wish w " +
			"join w.product p " +
			"join p.member m " +
			"join p.town t " +
			"join t.coordinate c " +
			"left join p.chatroomList cr " +
			"left join cr.product p1 " +
			"	on p1.member.id = :memberId " +
			"where w.member.id = :memberId")
	Page<Wish> findAllByMemberId(@Param("memberId") Long memberId, Pageable pageable);

	void deleteByMemberIdAndProductId(Long memberId, Long productId);

	boolean existsByMemberIdAndProductId(Long memberId, Long productId);
}
