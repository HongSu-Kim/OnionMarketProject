package com.youprice.onion.service.order.impl;

import com.youprice.onion.dto.order.WishListDTO;
import com.youprice.onion.entity.chat.Chatroom;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.order.Wish;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.repository.chat.ChatroomRepository;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.order.WishRepository;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.service.order.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class WishServiceImpl implements WishService {

    private final WishRepository wishRepository;
	private final MemberRepository memberRepository;
	private final ProductRepository productRepository;
	private final ChatroomRepository chatroomRepository;

	// 찜 리스트 조회
	@Override
	public Page<WishListDTO> getWishList(Long memberId, Pageable pageable) {
		return wishRepository.findAllByMemberId(memberId, pageable).map(wish -> {

			WishListDTO wishListDTO = new WishListDTO(wish);

			Chatroom chatroom = chatroomRepository.findByMemberIdAndProductId(memberId, wish.getProduct().getId()).orElse(null);
			if (chatroom != null) {
				wishListDTO.setChatroomId(chatroom.getId());
			}

			return wishListDTO;
		});
	}

	// 찜 등록
	@Override
	public int addWish(Long memberId, Long productId) {
		if (!wishRepository.existsByMemberIdAndProductId(memberId, productId)) { // 중복 방지

			Member member = memberRepository.findById(memberId).orElse(null);
			Product product = productRepository.findById(productId).orElse(null);

			// 자신의 상품을 찜할때 0 리턴
			if (member == product.getMember()) {
				return 0;
			}

			wishRepository.save(new Wish(member, product));
		}
		return wishRepository.countByMemberId(memberId);
	}

	// 찜 삭제
	@Override
	public int removeWish(Long memberId, Long productId) {
		wishRepository.deleteByMemberIdAndProductId(memberId, productId);

		return wishRepository.countByMemberId(memberId);
	}

}
