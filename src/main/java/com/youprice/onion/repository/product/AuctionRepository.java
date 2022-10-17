package com.youprice.onion.repository.product;

import com.youprice.onion.entity.product.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    @Override
    Optional<Auction> findById(Long auctionId);
    //경매현황으로 경매번호 조회
    Optional<Auction> findByAuctionStatus(String auctionStatus);
}
