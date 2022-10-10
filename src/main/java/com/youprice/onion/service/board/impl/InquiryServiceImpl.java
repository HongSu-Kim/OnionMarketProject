package com.youprice.onion.service.board.impl;

import com.youprice.onion.dto.board.InquiryDTO;
import com.youprice.onion.entity.board.Inquiry;
import com.youprice.onion.repository.board.InquiryRepository;
import com.youprice.onion.service.board.InquiryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;
    private final ModelMapper modelMapper;

    @Override
    public void save(Inquiry inquiry) {
        inquiryRepository.save(inquiry);
    }

    @Override
    public Inquiry findById(Long id) {
        Optional<Inquiry> inquiry = inquiryRepository.findById(id);

        if(inquiry.isPresent()){
            return inquiry.get();
        } else {
            throw new IllegalArgumentException();
        }
    }
    // 수정
    public void update(InquiryDTO inquiryDTO){

        Inquiry inquiry = modelMapper.map(inquiryDTO, Inquiry.class);
        inquiryRepository.save(inquiry);
    }
    // 삭제
    public void delete(InquiryDTO inquiryDTO){
        Inquiry inquiry = modelMapper.map(inquiryDTO, Inquiry.class);
        inquiryRepository.delete(inquiry);
    }

    @Override
    public Page<Inquiry> findAll(Pageable pageable) {
        Page<Inquiry> list = inquiryRepository.findAll(pageable);
        return list;
    }

    @Override
    public Page<Inquiry> findByUsernameContaining(String username, Pageable pageable) {
        Page<Inquiry> findInquiry = inquiryRepository.findInquiriesByMember_NameContainingOrderById(username,pageable);
        return  findInquiry;
    }
}
