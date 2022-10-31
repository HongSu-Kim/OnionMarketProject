package com.youprice.onion.service.member.impl;

import com.youprice.onion.dto.member.FollowDTO;
import com.youprice.onion.dto.member.FollowListDTO;
import com.youprice.onion.entity.member.Follow;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.repository.member.FollowRepository;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.service.member.FollowService;
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
public class FollowServiceImpl implements FollowService {

    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;

    //팔로우 리스트 조회
    @Override
    public Page<FollowDTO> getFollowList(Long memberId, Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(), Sort.Direction.DESC, "id");

        return followRepository.findAllByMemberId(memberId, pageable).map(FollowDTO::new);
    }
/*    @Override
    public Page<FollowListDTO> getFollowList(Long memberId, Pageable pageable) {
        return followRepository.findAllByMemberId(memberId, pageable).map(follow -> {
            int followListSize = followRepository.countByTargetId(follow.getTarget().getId());
            return new FollowListDTO(follow, followListSize);
        });
    }*/

    //팔로우
    @Override
    public void addFollow(Long memberId, Long targetId) {
        if (followRepository.existsByMemberIdAndTargetId(memberId, targetId)) return;

        Member member = memberRepository.findById(memberId).orElse(null);
        Member target = memberRepository.findById(targetId).orElse(null);

        followRepository.save(new Follow(member, target));
    }

    //언팔로우
    @Override
    public void removeFollow(Long memberId, Long targetId) {
        followRepository.deleteByMemberIdAndTargetId(memberId, targetId);
    }
}
