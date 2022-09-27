package com.youprice.onion.repository.order;

import com.youprice.onion.entity.order.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish, Integer> {

}
