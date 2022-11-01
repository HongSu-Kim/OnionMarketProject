package com.youprice.onion.service.member;

import com.youprice.onion.dto.member.FollowDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FollowService {

    Page<FollowDTO> getFollowList(Long memberId, Pageable pageable);
    void addFollow(Long memberId, Long targetId);
    void removeFollow(Long memberId, Long targetId);
}
