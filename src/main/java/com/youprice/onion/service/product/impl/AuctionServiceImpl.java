package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.product.AuctionAddDTO;
import com.youprice.onion.dto.product.AuctionFindDTO;
import com.youprice.onion.entity.product.Auction;
import com.youprice.onion.repository.product.AuctionRepository;
import com.youprice.onion.service.product.AuctionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;

    @Override
    @Transactional
    public Long addAuction(AuctionAddDTO auctionAddDTO) {

        Auction auction = new Auction(auctionAddDTO.getAuctionDeadLine(), auctionAddDTO.getAuctionStatus());

        Long auctionId = auctionRepository.save(auction).getId();

        return auctionId;
    }

    @Override
    public AuctionFindDTO findAuctionId(String auctionStatus) {
        return auctionRepository.findByAuctionStatus(auctionStatus).map(AuctionFindDTO::new).orElse(null);
    }
}
