package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.AuctionAddDTO;
import com.youprice.onion.dto.product.AuctionFindDTO;

import java.util.List;

public interface AuctionService {

    //경매 등록
    Long addAuction(AuctionAddDTO auctionAddDTO);
    //경매 현황으로 id조회
    AuctionFindDTO findAuctionId(String auctionStatus);
    //경매 기한 초기화

    //경매 여부 변경



}
