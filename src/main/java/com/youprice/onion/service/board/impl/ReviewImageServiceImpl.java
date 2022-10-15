package com.youprice.onion.service.board.impl;

import com.youprice.onion.dto.board.ReviewImageDTO;
import com.youprice.onion.entity.board.ReviewImage;
import com.youprice.onion.repository.board.ReviewImageRepository;
import com.youprice.onion.service.board.ReviewImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewImageServiceImpl implements ReviewImageService {

    private final ReviewImageRepository reviewImageRepository;

    public List<ReviewImageDTO> findByReviewId(Long id){
        return reviewImageRepository.findByReviewId(id)
                .stream().map(ReviewImageDTO::new).collect(Collectors.toList());
    }

    public void deleteImage(Long id){
        ReviewImage reviewImage = reviewImageRepository.findById(id).orElse(null);
        reviewImageRepository.delete(reviewImage);
    }
}
