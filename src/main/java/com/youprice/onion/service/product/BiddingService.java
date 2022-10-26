package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.BiddingAddDTO;
import com.youprice.onion.dto.product.BiddingListDTO;

import java.util.List;

public interface BiddingService {

    //상품 입찰
    Long bidProduct(BiddingAddDTO biddingAddDTO);

    //입찰목록 조회
    List<BiddingListDTO> getBiddingList(Long productId);

}
