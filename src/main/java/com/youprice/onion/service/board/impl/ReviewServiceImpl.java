package com.youprice.onion.service.board.impl;

import com.youprice.onion.dto.board.*;
import com.youprice.onion.entity.board.Review;
import com.youprice.onion.entity.board.ReviewImage;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.order.Order;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.repository.board.ReviewImageRepository;
import com.youprice.onion.repository.board.ReviewRepository;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.order.OrderRepository;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.service.board.ReviewService;
import com.youprice.onion.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public ReviewDTO getReviewDTO(Long reviewId) {
        return reviewRepository.findById(reviewId).map(ReviewDTO::new).orElse(null);
    }
    @Transactional
    public int saveReview(ReviewFormDTO form, List<MultipartFile> reviewImageName) throws IOException {
        Order order = orderRepository.findById(form.getOrderId()).orElse(null);
        Member member = memberRepository.findById(form.getMemberId()).orElse(null);

        Review review = new Review(order, member, form.getReviewContent(), form.getGrade());
        Review save = reviewRepository.save(review);

        // 판매자 평점 변경
        Product product = productRepository.findById(order.getProduct().getId()).orElse(null);
        Member salesUser = memberRepository.findById(product.getMember().getId()).orElse(null);
        Double gradeAverage = reviewRepository.gradeAverage(salesUser.getId());
        salesUser.updateGrade(gradeAverage);


        List<String> storeName = ImageUtil.store(reviewImageName, "review");
        for (String storeImageName : storeName) {
            ReviewImage reviewImage = new ReviewImage(save, storeImageName);
            reviewImageRepository.save(reviewImage);
        }

        int point = 0;
        if (save.getId() != null) {
            if (storeName.size() != 0) {
                point = member.addPoint(150);// 사진리뷰
            } else {
                point = member.addPoint(50); // 일반리뷰
            }
        }
        return point;
    }

    public ReviewDTO findReviewDTO(Long reviewId) {
        return reviewRepository.findById(reviewId).map(ReviewDTO::new).orElse(null);
    }

    // 특정 회원의 목록
    public Page<ReviewDTO> userReviewList(Long salesId, Pageable pageable) {
        return reviewRepository.salesUserReviewList(salesId, pageable).map(ReviewDTO::new);
    }
    // 내가 남긴 리뷰
    public Page<ReviewDTO> myReviewList(Long memberId, Pageable pageable) {
        return reviewRepository.findAllByMemberIdOrderByIdDesc(memberId, pageable).map(ReviewDTO::new);
    }

    @Override
    public Page<ReviewDTO> findAll(Pageable pageable) {
        Page<ReviewDTO> list = reviewRepository.findAll(pageable).map(ReviewDTO::new);
        return list;
    }

    // 수정
    @Transactional
    public void updateReview(Long reviewId, ReviewUpdateDTO form) throws IOException {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new NoSuchElementException());
        Order order = orderRepository.findById(review.getOrder().getId()).orElse(null);

        List<String> storeName = ImageUtil.store(form.getReviewImageName(), "review");
        for (String storeImageName : storeName) {
            ReviewImage reviewImage = new ReviewImage(review, storeImageName);
            reviewImageRepository.save(reviewImage);
        }
        review.updateReview(reviewId, form);
        reviewRepository.save(review);

        // 판매자 평점 변경
        Product product = productRepository.findById(order.getProduct().getId()).orElse(null);
        Member salesUser = memberRepository.findById(product.getMember().getId()).orElse(null);
        Double gradeAverage = reviewRepository.gradeAverage(salesUser.getId());
        salesUser.updateGrade(gradeAverage);
    }

    // 삭제
    @Transactional
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
        if(reviewImageRepository.findByReviewId(reviewId) != null){
            List<ReviewImage> reviewImage = reviewImageRepository.findByReviewId(reviewId);
            for(ReviewImage reviewImage1 : reviewImage){
                ImageUtil.delete(reviewImage1.getStoreImageName(), "review");
            }
        }
    }

    public void deleteImage(Long imageId) {
        ReviewImage reviewImage = reviewImageRepository.findById(imageId).orElse(null);
        reviewImageRepository.delete(reviewImage);
    }

}

