package com.youprice.onion.repository.board;

import com.youprice.onion.entity.board.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> { //JpaRepository<엔티티, pk의 타입>

}
