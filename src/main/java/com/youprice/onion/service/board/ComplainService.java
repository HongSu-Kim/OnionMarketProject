package com.youprice.onion.service.board;

import com.youprice.onion.dto.board.ComplainDTO;
import com.youprice.onion.dto.board.ComplainFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ComplainService {

    ComplainDTO findComplainDTO(Long id);
    void saveComplain(ComplainFormDTO complainFormDTO);
    Page<ComplainDTO> findAll(Pageable pageable);
}
