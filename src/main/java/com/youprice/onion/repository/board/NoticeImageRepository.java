package com.youprice.onion.repository.board;

import com.youprice.onion.entity.board.NoticeImage;
import com.youprice.onion.entity.board.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeImageRepository extends JpaRepository<NoticeImage, Long> {
    List<NoticeImage> findByNoticeId(Long NoticeId);

}
