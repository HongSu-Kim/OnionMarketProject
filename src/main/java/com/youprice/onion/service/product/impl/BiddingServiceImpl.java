package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.product.BiddingAddDTO;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Bidding;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.product.BiddingRepository;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.service.product.BiddingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BiddingServiceImpl implements BiddingService {

    private final BiddingRepository biddingRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long bidProduct(BiddingAddDTO biddingAddDTO) {

        Product product = productRepository.findById(biddingAddDTO.getProductId()).orElse(null);
        Member member = memberRepository.findById(biddingAddDTO.getMemberId()).orElse(null);

        Bidding bidding = new Bidding(product,member,biddingAddDTO);

        return biddingRepository.save(bidding).getId();
    }
}
