package com.youprice.onion.repository.order;

import com.youprice.onion.dto.order.OrderDTO;
import com.youprice.onion.entity.order.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	// orderNum 중복확인
    Optional<Order> findByOrderNum(String orderNum);

	// order + delivery
	@Override
	@EntityGraph(attributePaths = "delivery")
	Optional<Order> findById(Long ID);

	// buyList
//	@Query(value = "select o.*, p.*, m.* from orders o join product p on o.product_id = p.product_id join member m on p.member_id = m.member_id where o.member_id = :memberId", nativeQuery = true)
	@EntityGraph(attributePaths = "product")
	List<Order> findAllByMemberId(Long memberId);


	// sellList
	@EntityGraph(attributePaths = "product")
	List<Order> findAllByProductMemberId(Long memberId);

}
