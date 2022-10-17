package com.youprice.onion.repository.order;

import com.youprice.onion.entity.order.Delivery;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

//	@EntityGraph("order, member")
//	Optional<Delivery> findById(Long deliveryId);

}
