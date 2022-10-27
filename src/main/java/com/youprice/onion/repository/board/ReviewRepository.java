package com.youprice.onion.repository.board;

import com.youprice.onion.entity.board.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByOrder_Member_UserId(String userId);

    Page<Review> findAllBySalesIdOrderById(Long salesId, Pageable pageable);
    @Query(value = "select ROUND(AVG(NVL(r.grade, 0)),1) from Review r where r.salesId = :salesId", nativeQuery = true)
    double gradeAverage(Long salesId);
}
