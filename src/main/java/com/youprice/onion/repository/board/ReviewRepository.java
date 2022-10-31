package com.youprice.onion.repository.board;

import com.youprice.onion.entity.board.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllBySalesIdOrderById(Long salesId, Pageable pageable);
    Page<Review> findAllByMemberIdOrderById(Long memberId, Pageable pageable);
    @Query(value = "select ROUND(AVG(NVL(r.grade, 0)),1) from Review r where r.salesId = :salesId", nativeQuery = true)
    double gradeAverage(Long salesId);
}
