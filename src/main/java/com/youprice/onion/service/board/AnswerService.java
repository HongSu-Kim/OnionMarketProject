package com.youprice.onion.service.board;

import com.youprice.onion.dto.board.AnswerDTO;
import com.youprice.onion.dto.board.AnswerFormDTO;

import java.util.List;

public interface AnswerService {

    AnswerDTO findAnswerDTO(Long answerId);
    Long saveAnswer(AnswerFormDTO answerFormDTO);
    void updateAnswer(Long answerId, AnswerFormDTO form);
    void deleteAnswer(AnswerDTO answerDTO);
    List<AnswerDTO> findByInquiryId(Long inquiryId);
}
