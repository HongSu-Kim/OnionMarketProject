package com.youprice.onion.repository.board;


import com.youprice.onion.entity.board.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> { //JpaRepository<엔티티, pk의 타입>

    @Modifying
    @Query("update Notice n set n.hitCount = n.hitCount + 1 where n.id =:id")
    int updateView(@Param("id")Long id);

    /*
    @Query("select n from Notice n where n.noticeType like :type and n.noticeSubject like %:subject%")
    Page<Notice> searchSubject(String type, String subject, Pageable pageable);
    */
}
