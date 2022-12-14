package com.youprice.onion.repository.board;


import com.youprice.onion.entity.board.Notice;
import com.youprice.onion.entity.board.NoticeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Modifying
    @Query(value = "update Notice n set n.hitCount = n.hitCount + 1 where n.id =:id")
    int updateView(@Param("id")Long id);

    Page<Notice> findAllByNoticeSubjectContainingAndNoticeTypeLike(String word, NoticeType noticeType, Pageable pageable);

    // 공지사항 전체
    Page<Notice> findAllByNoticeTypeLikeOrderByIdDesc(NoticeType notice, Pageable pageable);
    // 자주하는 질문
    List<Notice> findAllByNoticeTypeLikeOrderByIdDesc(NoticeType notice);
}
