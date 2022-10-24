package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.BiddingAddDTO;
import com.youprice.onion.dto.product.BiddingListDTO;

import java.util.List;

public interface BiddingService {

    Long bidProduct(BiddingAddDTO biddingAddDTO);

    List<BiddingListDTO> getBiddingList(Long productId);
}
