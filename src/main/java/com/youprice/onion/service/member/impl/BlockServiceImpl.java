package com.youprice.onion.service.member.impl;

import com.youprice.onion.dto.member.BlockListDTO;
import com.youprice.onion.entity.member.Block;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.repository.member.BlockRepository;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.service.member.Blockservice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BlockServiceImpl implements Blockservice {

    private final MemberRepository memberRepository;
    private final BlockRepository blockRepository;

    //차단 리스트 조회
    @Override
    public Page<BlockListDTO> getBlockList(Long memberId, Pageable pageable) {
        return blockRepository.findAllByMemberId(memberId, pageable).map(block -> {
            int blockListSize = blockRepository.countByTargetId(block.getTarget().getId());
            return new BlockListDTO(block, blockListSize);
        });
    }

    //팔로우
    @Override
    public void addBlock(Long memberId, Long targetId) {
        if (blockRepository.existsByMemberIdAndTargetId(memberId, targetId)) return;

        Member member = memberRepository.findById(memberId).orElse(null);
        Member target = memberRepository.findById(targetId).orElse(null);

        blockRepository.save(new Block(member, target));
    }

    //언팔로우
    @Override
    public void removeBlock(Long memberId, Long targetId) {
        blockRepository.deleteByMemberIdAndTargetId(memberId, targetId);
    }
}
