package com.youprice.onion.repository.order;

import com.youprice.onion.entity.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	// 주문 내역 조회 - detail
	@EntityGraph(attributePaths = { "member", "delivery", "product" })
	Optional<Order> findById(Long orderId);

	// orderNum 중복확인
    Optional<Order> findByOrderNum(String orderNum);

	
	// 구매 내역 조회 - list
	@EntityGraph(attributePaths = { "member", "delivery", "product" })
	Page<Order> findAllByMemberId(Long memberId, Pageable pageable);

}
