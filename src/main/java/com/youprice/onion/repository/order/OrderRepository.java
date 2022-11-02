package com.youprice.onion.repository.order;

import com.youprice.onion.entity.order.Order;
import com.youprice.onion.entity.order.OrderState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	// 주문 내역 조회 - detail
//	@EntityGraph(attributePaths = { "member", "delivery", "product", "reviewList" })
	@Query(value = "select distinct * " +
					"from Orders o " +
					"join Member m " +
					"	on o.member_id = m.member_id " +
					"join Product p " +
					"	on o.product_id = p.product_id " +
					"left join Delivery d " +
					"	on o.order_id = d.order_id " +
					"left join Review r " +
					"	on o.order_id = r.order_id " +
					"	and o.member_id = r.member_id " +
					"where o.order_id = :orderId ",
			nativeQuery = true)
	Optional<Order> findById(@Param("orderId") Long orderId);

	// orderNum 중복확인
    Optional<Order> findByOrderNum(String orderNum);

	
	// 구매 내역 조회 - list
//	@EntityGraph(attributePaths = { "member", "delivery", "product", "reviewList" })
	@Query(value = "select distinct * " +
					"from Orders o " +
					"join Member m " +
					"	on o.member_id = m.member_id " +
					"join Product p " +
					"	on o.product_id = p.product_id " +
					"left join Delivery d " +
					"	on o.order_id = d.order_id " +
					"left join Review r " +
					"	on o.order_id = r.order_id " +
					"	and o.member_id = r.member_id " +
					"where o.member_id = :memberId ",
			nativeQuery = true)
	Page<Order> findDistinctByMemberId(@Param("memberId") Long memberId, Pageable pageable);

	// 거래중인 order
	Optional<Order> findByProductIdAndOrderState(Long productId, OrderState orderState);
}
