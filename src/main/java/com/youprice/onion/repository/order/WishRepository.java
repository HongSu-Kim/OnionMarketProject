package com.youprice.onion.repository.order;

import com.youprice.onion.entity.order.Wish;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

	@EntityGraph(attributePaths = "product")
	List<Wish> findAllByMemberId(Long memberId);

}
