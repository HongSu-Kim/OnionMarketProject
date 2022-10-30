package com.youprice.onion.service.member;

import com.youprice.onion.dto.member.BlockListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Blockservice {

    Page<BlockListDTO> getBlockList(Long memberId, Pageable pageable);
    void addBlock(Long memberId, Long targetId);
    void removeBlock(Long memberId, Long targetId);
}
