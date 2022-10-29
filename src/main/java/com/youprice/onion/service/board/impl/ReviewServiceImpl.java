package com.youprice.onion.service.board.impl;

import com.youprice.onion.dto.board.*;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.order.OrderDTO;
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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final MemberRepository memberRepository;

    public ReviewDTO getReviewDTO(Long reviewId){
        return reviewRepository.findById(reviewId).map(ReviewDTO::new).orElse(null);
    }
    @Transactional
    public int saveReview(ReviewFormDTO form, List<MultipartFile> reviewImageName) throws IOException {
        Order order = orderRepository.findById(form.getOrderId()).orElse(null);
        Member member = memberRepository.findById(form.getMemberId()).orElse(null);

        Review review = new Review(order, member, form.getReviewContent(), form.getGrade(), form.getSalesId());
        Review save = reviewRepository.save(review);
        Long reviewId = save.getId();

        List<ReviewImage> list = storeImages(reviewId, reviewImageName);
        for(ReviewImage reviewImage : list){
            reviewImageRepository.save(reviewImage);
        }
        int point = 0;

        if(save.getId() != null){
            if(list.size() != 0) {
                point = member.addPoint(150);// 사진리뷰
            } else {
                point = member.addPoint(50); // 일반리뷰
            }
        }
        return point;
    }

    public ReviewDTO findByUserId(String userId){
        Review review = reviewRepository.findByOrder_Member_UserId(userId).orElse(null);
        ReviewDTO reviewDTO = new ReviewDTO(review);
        return reviewDTO;
    }

    public ReviewDTO findReviewDTO(Long reviewId){
        return reviewRepository.findById(reviewId).map(ReviewDTO::new).orElse(null);
    }
    // 판매자 DTO 찾아오기
    public MemberDTO getSalesUserName(OrderDTO orderDTO){
        Product product = productRepository.findById(orderDTO.getProductId()).orElse(null);
        MemberDTO memberDTO = memberRepository.findById(product.getMember().getId())
                                .map(MemberDTO::new).orElse(null);
        return memberDTO;
    }

    // 특정 회원의 목록
    public Page<ReviewDTO> userReviewList(Long salesId, Pageable pageable){
        return reviewRepository.findAllBySalesIdOrderById(salesId, pageable).map(ReviewDTO::new);
    }

    @Override
    public Page<ReviewDTO> findAll(Pageable pageable) {
        Page<ReviewDTO> list = reviewRepository.findAll(pageable).map(ReviewDTO::new);
        return list;
    }
    public List<ReviewDTO> findAll(){
        return reviewRepository.findAll().stream().map(ReviewDTO::new).collect(Collectors.toList());
    }

    // 수정
    @Transactional
    public void updateReview(Long reviewId, ReviewUpdateDTO form) throws IOException {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new NoSuchElementException());
        Long id = review.getId();
        review.updateReview(id, form);

        List<ReviewImage> list = storeImages(reviewId, form.getReviewImageName());
        for(ReviewImage reviewImage : list){
            reviewImageRepository.save(reviewImage);
        }
        reviewRepository.save(review);
    }
    // 삭제
    @Transactional
    public void deleteReview(ReviewDTO reviewDTO){
        Review review = reviewRepository.findById(reviewDTO.getReviewId()).orElse(null);
        reviewRepository.delete(review);

        List<ReviewImageDTO> reviewImageList = reviewDTO.getReviewImageList();
        for(ReviewImageDTO reviewImageDTO : reviewImageList) {
            String filePath = filePath();
            File file = new File(filePath, reviewImageDTO.getStoreImageName());
            file.delete();
        }
    }

    //=======================================================================================
    public List<ReviewImage> storeImages(Long reviewId, List<MultipartFile> multipartFiles) throws IOException {
        List<ReviewImage> storeFileList = new ArrayList<>();
        Review review = reviewRepository.findById(reviewId).orElse(null);
        for (MultipartFile multipartFile : multipartFiles) {

            if(!multipartFile.isEmpty()){
                String originalFilename = multipartFile.getOriginalFilename(); // 원본파일명
                String storeImageName = storePath(multipartFile); // uuid 변환
                ReviewImage reviewImage = new ReviewImage(review, originalFilename, storeImageName);
                storeFileList.add(reviewImage);
            }
        }
        return storeFileList;
    }
    public String filePath(){
        return System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\review";
    }
    public String storePath(MultipartFile multipartFile) throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\review";

        if(multipartFile.isEmpty()){
            return null;
        }
        String originalFilename = multipartFile.getOriginalFilename();

        // 확장자만 추출
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String uuid = UUID.randomUUID().toString();
        String storeFileName = uuid + "." + ext;

        multipartFile.transferTo(new File(filePath, storeFileName));

        return storeFileName;
    }

    public double avgGrade(Long salesId){
        return reviewRepository.gradeAverage(salesId);
    }

}

