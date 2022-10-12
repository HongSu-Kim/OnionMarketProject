package com.youprice.onion.repository.board;

import com.youprice.onion.entity.board.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByOrder_Member_UserId(String userId);
}
