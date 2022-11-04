package com.youprice.onion.repository.board;

import com.youprice.onion.entity.board.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByMemberIdOrderByIdDesc(Long memberId, Pageable pageable);

    @Query(value = "select ROUND(AVG(NVL(r.grade, 0)),1) from Review r left join Order o on r.order.id = o.id "
           + "left join Product p on o.product.id = p.id where p.member.id = :salesId")
    Double gradeAverage(@Param("salesId") Long salesId);


    @Query(value = "select r from Review r left join Order o on r.order.id = o.id left join Product p "
        + "on o.product.id = p.id where p.member.id = :salesId")
    Page<Review> salesUserReviewList(@Param("salesId") Long salesId, Pageable pageable);

}
