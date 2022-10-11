package com.youprice.onion.service.board;

import com.youprice.onion.dto.board.InquiryDTO;
import com.youprice.onion.entity.board.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InquiryService {

    void save(Inquiry inquiry);
    Inquiry findById(Long id);
    void update(InquiryDTO inquiryDTO);
    void delete(InquiryDTO inquiryDTO);

    Page<Inquiry> findAll(Pageable pageable);
    Page<Inquiry> findByUsernameContaining(String username, Pageable pageable);
}
