package com.youprice.onion.service.board.impl;

import com.youprice.onion.entity.board.Review;
import com.youprice.onion.entity.board.ReviewImage;
import com.youprice.onion.repository.board.ReviewImageRepository;
import com.youprice.onion.repository.board.ReviewRepository;
import com.youprice.onion.service.board.ReviewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ModelMapper modelMapper;
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository
            reviewImageRepository;

    /*
    public ReviewDTO getReviewDTO(Long reviewId){
        return modelMapper.map(reviewRepository.findById(reviewId).orElse(null), ReviewDTO.class);
    }*/

    public void addReview(Long reviewId, List<MultipartFile> imageFiles) throws IOException {
        List<ReviewImage> list = storeImages(reviewId, imageFiles);

        for(ReviewImage reviewImage : list){
            reviewImageRepository.save(reviewImage);
        }
    }

    public Long save(Review review){
        reviewRepository.save(review);
        return review.getId();
    }

    public Optional<Review> findByUserId(String userId){
        return findAll().stream().filter(r -> r.getOrder().getMember().getUserId().equals(userId)).findFirst();
    }

    public Review findById(Long id){
        Optional<Review> review= reviewRepository.findById(id);

        if(review.isPresent()){
            return review.get();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public List<Review> findAll(){
        List<Review> allReview = reviewRepository.findAll();
        return allReview;
    }

    //=======================================================================================
    public List<ReviewImage> storeImages(Long reviewId, List<MultipartFile> multipartFiles) throws IOException {
        List<ReviewImage> storeFileList = new ArrayList<>();
        Review review = findById(reviewId);

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

        // 넘기는건 중복되지 않게 생성된 파일경로
        return storeFileName;
    }

}

