package com.youprice.onion.service.board.impl;

import com.youprice.onion.dto.board.AnswerDTO;
import com.youprice.onion.dto.board.AnswerFormDTO;
import com.youprice.onion.entity.board.Answer;
import com.youprice.onion.entity.board.Inquiry;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.repository.board.AnswerRepository;
import com.youprice.onion.repository.board.InquiryRepository;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.service.board.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 읽기 전용메소드에 사용 (약간의 성능향상)
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;
    private final InquiryRepository inquiryRepository;


    @Override
    public AnswerDTO findAnswerDTO(Long answerId) {
        return answerRepository.findById(answerId).map(AnswerDTO::new).orElse(null);
    }

    @Override
    @Transactional
    public Long saveAnswer(AnswerFormDTO answerFormDTO) {
        Inquiry inquiry = inquiryRepository.findById(answerFormDTO.getInquiryId()).orElse(null);
        Member member = memberRepository.findById(answerFormDTO.getMemberId()).orElse(null);
        Answer answer = new Answer(inquiry, member, answerFormDTO.getAnswerContent());

        Answer saveAnswer = answerRepository.save(answer);
        if(saveAnswer != null){
            inquiry.modifyStatus("complete");
        }
        return answer.getId();
    }

    @Override
    @Transactional
    public void updateAnswer(Long answerId, AnswerFormDTO form) {
        Answer answer = answerRepository.findById(answerId).orElse(null);
        answer.updateAnswer(answerId, form.getAnswerContent());
        answerRepository.save(answer);
    }

    @Override
    @Transactional
    public void deleteAnswer(AnswerDTO answerDTO) {
        Answer answer = answerRepository.findById(answerDTO.getAnswerId()).orElse(null);
        answerRepository.delete(answer);
    }

    public List<AnswerDTO> findByInquiryId(Long inquiryId){
        return answerRepository.findByInquiry_Id(inquiryId).stream().map(AnswerDTO::new).collect(Collectors.toList());
    }

}
