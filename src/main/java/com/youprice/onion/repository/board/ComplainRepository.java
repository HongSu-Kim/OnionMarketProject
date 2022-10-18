package com.youprice.onion.repository.board;

import com.youprice.onion.entity.board.Complain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplainRepository extends JpaRepository<Complain, Long> {

}
