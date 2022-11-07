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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;
    private final MemberRepository memberRepository;

    @Override
    public InquiryDTO findInquiryDTO(Long inquiryId) {
        return inquiryRepository.findById(inquiryId).map(InquiryDTO::new).orElse(null);
    }
    // 저장
    @Override
    @Transactional
    public Long saveInquiry(InquiryFormDTO inquiryFormDTO) {
        Member member = memberRepository.findById(inquiryFormDTO.getMemberId()).orElse(null);
        Inquiry inquiry = new Inquiry(member, inquiryFormDTO);
        inquiryRepository.save(inquiry);
        return inquiry.getId();
    }
    // 수정
    @Transactional
    public void updateInquiry(Long inquiryId, InquiryFormDTO form){
        Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(()-> new NoSuchElementException());
        Long id = inquiry.getId();
        inquiry.updateInquiry(id, form);

        inquiryRepository.save(inquiry);
    }
    // 삭제
    @Transactional
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
    // 특정회원의 문의내역
    public Page<InquiryDTO> MemberReviewList(Long memberId, Pageable pageable){
        return inquiryRepository.findAllByMember_Id(memberId,pageable).map(InquiryDTO::new);
    }

    // 검색
    public Page<InquiryDTO> getSearchList(String field, String word, Pageable pageable){
        if(field.equals("name")) {
            return inquiryRepository.findAllByMember_NicknameContaining(word, pageable).map(InquiryDTO::new);
        } else if(field.equals("all")){
            return inquiryRepository.findAllByInquirySubjectContaining(word, pageable).map(InquiryDTO::new);
        } else {
            return inquiryRepository.findAllByInquiryTypeContainingAndInquirySubjectContaining(field, word, pageable).map(InquiryDTO::new);
        }
    }

    // 회원 1명의 전체 문의 기간 검색
    public Page<InquiryDTO> getPeriodSearch(String dt_fr, String dt_to, String status, Long memberId, Pageable pageable){

        String from = dt_fr.replace(".", "-");
        String to = dt_to.replace(".", "-");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fromDate = LocalDate.parse(from, dateTimeFormatter);
        LocalDate toDate = LocalDate.parse(to, dateTimeFormatter);

        if(status.equals("")){
            return inquiryRepository.findPeriod(fromDate, toDate, memberId, pageable).map(InquiryDTO::new);
        } else {
            return inquiryRepository.findByPeriodandStatus(fromDate,toDate,status,memberId,pageable).map(InquiryDTO::new);
        }
    }
    public Page<InquiryDTO> listByStatus(String status, Long memberId, Pageable pageable){
        return inquiryRepository.findByStatus(status, memberId, pageable).map(InquiryDTO::new);
    }

    // 모든 문의 기간 검색
    public Page<InquiryDTO> allListByPeriod(String dt_fr, String dt_to, Pageable pageable){

        String from = dt_fr.replace(".", "-");
        String to = dt_to.replace(".", "-");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fromDate = LocalDate.parse(from, dateTimeFormatter);
        LocalDate toDate = LocalDate.parse(to, dateTimeFormatter);

        return inquiryRepository.findAllByPeriod(fromDate, toDate, pageable).map(InquiryDTO::new);
    }

    // 모든 문의 기간+단어 검색
    public Page<InquiryDTO> allListSearch(String dt_fr, String dt_to, String field, String word, Pageable pageable){
        String from = dt_fr.replace(".", "-");
        String to = dt_to.replace(".", "-");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fromDate = LocalDate.parse(from, dateTimeFormatter);
        LocalDate toDate = LocalDate.parse(to, dateTimeFormatter);

        if(field.equals("name")) {
            return inquiryRepository.searchNamePeriod(fromDate, toDate, word, pageable).map(InquiryDTO::new);
        } else if(field.equals("all")){
            return inquiryRepository.searchAllByPeriod(fromDate, toDate, word, pageable).map(InquiryDTO::new);
        } else {
            return inquiryRepository.searchTypePeriod(fromDate, toDate, field, word, pageable).map(InquiryDTO::new);
        }
    }

}
