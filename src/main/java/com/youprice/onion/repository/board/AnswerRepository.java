package com.youprice.onion.repository.board;

import com.youprice.onion.entity.board.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByInquiry_Id(Long inquiryId);
}
