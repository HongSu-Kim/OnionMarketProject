package com.youprice.onion.service.board;

import com.youprice.onion.dto.board.ComplainDTO;
import com.youprice.onion.dto.board.ComplainFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ComplainService {

    ComplainDTO saveComplain(ComplainFormDTO form);
    String modifyStatus(Long complainId, String select);
    Page<ComplainDTO> findAll(Pageable pageable);
}
