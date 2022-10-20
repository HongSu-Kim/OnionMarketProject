package com.youprice.onion.repository.order;

import com.youprice.onion.entity.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	// orderNum 중복확인
    Optional<Order> findByOrderNum(String orderNum);

	// buyList
//	@Query(value = "select o from Order o join fetch o.product p join fetch p.member m " +
//			"left join fetch o.delivery left join fetch p.auction where o.member.id = :memberId order by o.id")
//	List<Order> findAllByMemberId(@Param("memberId") Long memberId, Pageable pageable);
	Long countByMemberId(Long memberId);
	@EntityGraph(attributePaths = { "product", "delivery" })
	Page<Order> findAllByMemberId(Long memberId, Pageable pageable);

}
