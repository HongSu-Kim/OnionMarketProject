package com.youprice.onion.service.board.impl;

import com.youprice.onion.dto.board.InquiryDTO;
import com.youprice.onion.dto.board.InquiryFormDTO;
import com.youprice.onion.entity.board.Inquiry;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.repository.board.InquiryRepository;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.service.board.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;
    private final MemberRepository memberRepository;

    // 저장
    @Override
    public void saveInquiry(InquiryFormDTO inquiryFormDTO) {
        Member member = memberRepository.findById(inquiryFormDTO.getMemberId()).orElse(null);
        Inquiry inquiry = new Inquiry(member, inquiryFormDTO);
        inquiryRepository.save(inquiry);
    }
    @Override
    public InquiryDTO findInquiryDTO(Long id) {
        return inquiryRepository.findById(id).map(InquiryDTO::new).orElse(null);
    }
    // 수정
    @Transactional
    public void updateInquiry(Long id, InquiryFormDTO form){
        Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(()-> new NoSuchElementException());
        Long inquiryId = inquiry.getId();
        inquiry.updateInquiry(inquiryId, form);

        inquiryRepository.save(inquiry);
    }
    // 삭제
    public void deleteInquiry(InquiryDTO inquiryDTO){
        Inquiry inquiry = inquiryRepository.findById(inquiryDTO.getInquiryId()).orElse(null);
        inquiryRepository.delete(inquiry);
    }

    // 페이징 리스트
    @Override
    public Page<InquiryDTO> findAll(Pageable pageable) {
        Page<InquiryDTO> list = inquiryRepository.findAll(pageable).map(InquiryDTO::new);
        return list;
    }
    // 검색
    public Page<InquiryDTO> getSearchList(String field, String word, Pageable pageable){
        if(field.equals("name")) {
            return inquiryRepository.findInquiriesByMember_NameContainingOrderById(word, pageable).map(InquiryDTO::new);
        } else  {
            return inquiryRepository.findInquiriesByInquiryTypeLikeAndInquirySubjectContainingOrderById(field, word, pageable).map(InquiryDTO::new);
        }
    }



}
