package com.youprice.onion.service.board;

import com.youprice.onion.dto.board.ComplainDTO;
import com.youprice.onion.dto.board.ComplainFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ComplainService {

    ComplainDTO saveComplain(ComplainFormDTO form);
    List<ComplainDTO> checkComplain(Long memberId);
    String modifyStatus(Long complainId, String select);
    Page<ComplainDTO> findAll(Pageable pageable);
}
