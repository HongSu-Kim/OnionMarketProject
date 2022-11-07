package com.youprice.onion.service.member;

import com.youprice.onion.dto.member.BlockDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlockService {

    Page<BlockDTO> getBlockList(Long memberId, Pageable pageable);
    void addBlock(Long memberId, Long targetId);
    void removeBlock(Long memberId, Long targetId);
    List<Long> blockIdList (Long memberId);
}
