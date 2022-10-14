package com.youprice.onion.service.order.impl;

import com.youprice.onion.dto.order.WishDTO;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.order.Wish;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.order.WishRepository;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.service.order.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class WishServiceImpl implements WishService {

    private final WishRepository wishRepository;
	private final MemberRepository memberRepository;
	private final ProductRepository productRepository;

	@Override
	public Page<WishDTO> getWishList(Long memberId, Pageable pageable) {
		return wishRepository.findAllByMemberId(memberId, pageable).map(WishDTO::new);
	}

	@Override
	public void addWish(Long memberId, Long productId) {

		Member member = memberRepository.findById(memberId).orElse(null);
		Product product = productRepository.findById(productId).orElse(null);

		wishRepository.save(new Wish(member, product));
	}

	@Override
	public void removeWish(Long wishId) {
		wishRepository.deleteById(wishId);
	}

}
