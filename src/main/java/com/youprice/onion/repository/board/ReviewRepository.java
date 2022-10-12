package com.youprice.onion.repository.board;

import com.youprice.onion.entity.board.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByOrder_Member_UserId(String userId);

    // 컬럼sellerId가 들어온id
    @Query(value = "select * from Review where sellerId = ?1", nativeQuery = true)
    List<Review> findList(Long sellerId);
}
