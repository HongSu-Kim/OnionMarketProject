package com.youprice.onion.service.member.impl;

import com.youprice.onion.dto.member.BlockDTO;
import com.youprice.onion.entity.member.Block;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.repository.member.BlockRepository;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.service.member.BlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BlockServiceImpl implements BlockService {

    private final MemberRepository memberRepository;
    private final BlockRepository blockRepository;

    //차단 리스트 조회
    @Override
    public Page<BlockDTO> getBlockList(Long memberId, Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(), Sort.Direction.DESC, "id");

        return blockRepository.findAllByMemberId(memberId, pageable).map(BlockDTO::new);
    }

    //차단
    @Override
    public void addBlock(Long memberId, Long targetId) {
        if (blockRepository.existsByMemberIdAndTargetId(memberId, targetId)) return;

        Member member = memberRepository.findById(memberId).orElse(null);
        Member target = memberRepository.findById(targetId).orElse(null);

        blockRepository.save(new Block(member, target));
    }

    //차단해제
    @Override
    public void removeBlock(Long memberId, Long targetId) {
        blockRepository.deleteByMemberIdAndTargetId(memberId, targetId);
    }
}
