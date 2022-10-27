package com.youprice.onion.repository.member;

import com.youprice.onion.entity.member.Follow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    int countByTargetId(Long targeId);

    Page<Follow> findAllByMemberId(Long memberId, Pageable pageable);

    boolean existsByMemberIdAndTargetId(Long memberId, Long targetId);

    void deleteByMemberIdAndTargetId(Long memberId, Long targetId);

    @Override
    Optional<Follow> findById(Long targetId);
}
