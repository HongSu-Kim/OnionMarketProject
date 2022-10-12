package com.youprice.onion.service.board.impl;

import com.youprice.onion.dto.board.ReviewDTO;
import com.youprice.onion.dto.board.ReviewFormDTO;
import com.youprice.onion.entity.board.Review;
import com.youprice.onion.entity.board.ReviewImage;
import com.youprice.onion.entity.order.Order;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.repository.board.ReviewImageRepository;
import com.youprice.onion.repository.board.ReviewRepository;
import com.youprice.onion.repository.order.OrderRepository;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.service.board.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ReviewImageRepository reviewImageRepository;

    public ReviewDTO getReviewDTO(Long reviewId){
        return reviewRepository.findById(reviewId).map(ReviewDTO::new).orElse(null);
    }
    public Long saveReview(ReviewFormDTO form, List<MultipartFile> reviewImageName) throws IOException {
        //Order order = orderRepository.findById(form.getOrderId()).orElse(null);
        Order order = orderRepository.findById(1L).orElse(null);
        Long sellerId = findSellerId(1L);

        Review review = new Review(order, form.getReviewContent(), form.getGrade(),sellerId);
        Review save = reviewRepository.save(review);
        Long reviewId = save.getId();
        List<ReviewImage> list = storeImages(reviewId, reviewImageName);
        for(ReviewImage reviewImage : list){
            reviewImageRepository.save(reviewImage);
        }
        return reviewId;
    }
    public Long findSellerId(Long buyerId){
        Order order = orderRepository.findById(buyerId).orElse(null);
        Product product = productRepository.findById(order.getProduct().getId()).orElse(null);
        return product.getMember().getId();
    }

    public ReviewDTO findByUserId(String userId){
        Review review = reviewRepository.findByOrder_Member_UserId(userId).orElse(null);
        ReviewDTO reviewDTO = new ReviewDTO(review);
        return reviewDTO;
    }

    public ReviewDTO findReviewDTO(Long id){
        return reviewRepository.findById(id).map(ReviewDTO::new).orElse(null);
    }

    /* 특정 회원의 목록
    public List<ReviewDTO> userReviewList(Long buyerId, Long reviewId){

    }*/
    @Override
    public Page<ReviewDTO> findAll(Pageable pageable) {
        Page<ReviewDTO> list = reviewRepository.findAll(pageable).map(ReviewDTO::new);
        return list;
    }

    //=======================================================================================
    public List<ReviewImage> storeImages(Long reviewId, List<MultipartFile> multipartFiles) throws IOException {
        List<ReviewImage> storeFileList = new ArrayList<>();
        Review review = reviewRepository.findById(reviewId).orElse(null);
        for (MultipartFile multipartFile : multipartFiles) {

            if(!multipartFile.isEmpty()){
                String originalFilename = multipartFile.getOriginalFilename(); // 원본파일명
                String storeImageName = storePath(multipartFile); // uuid 반환
                ReviewImage reviewImage = new ReviewImage(review, originalFilename, storeImageName);
                storeFileList.add(reviewImage);
            }
        }
        return storeFileList;
    }
    public String filePath(){
        //user.dir -> C:\Users\osr\Desktop\study\OnionMarketProject
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images";
        return filePath;
    }
    public String storePath(MultipartFile multipartFile) throws IOException {

        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images";

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

}

