package com.youprice.onion.service.board;

import com.youprice.onion.dto.board.InquiryDTO;
import com.youprice.onion.dto.board.InquiryFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InquiryService {
    void saveInquiry(InquiryFormDTO inquiryFormDTO);
    InquiryDTO findInquiryDTO(Long id);
    void updateInquiry(Long id, InquiryFormDTO form);
    void deleteInquiry(InquiryDTO inquiryDTO);

    Page<InquiryDTO> findAll(Pageable pageable);
    Page<InquiryDTO> getSearchList(String field, String word, Pageable pageable);
}
