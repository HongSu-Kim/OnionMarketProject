package com.youprice.onion.repository.order;

import com.youprice.onion.entity.order.Delivery;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

	@Query(value = "select d from Delivery d join fetch d.order o join fetch o.product join fetch o.member where d.id = ?1")
	Optional<Delivery> findById(Long orderId);

	@Query(value = "select d from Delivery d where d.id = ?1")
	Optional<Delivery> findDeliveryById(Long orderId);
}
