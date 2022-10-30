package com.youprice.onion.repository.product;

import com.youprice.onion.entity.product.Bidding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiddingRepository extends JpaRepository<Bidding, Long> {

    List<Bidding> findByProductIdOrderByBidDesc(Long productId);
}
