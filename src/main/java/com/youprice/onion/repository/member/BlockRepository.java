package com.youprice.onion.repository.member;
import com.youprice.onion.dto.member.BlockDTO;
import com.youprice.onion.dto.member.BlockListDTO;
import com.youprice.onion.entity.member.Block;
import com.youprice.onion.entity.member.Follow;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {

    int countByTargetId(Long targeId);
    Page<Block> findAllByMemberId(Long memberId, Pageable pageable);
    boolean existsByMemberIdAndTargetId(Long memberId, Long targetId);
    void deleteByMemberIdAndTargetId(Long memberId, Long targetId);
    @Override
    Optional<Block> findById(Long targetId);
    @Query("select b.target.id from Block b where b.member.id = :memberId")
    List<Long> findBlockIdList(@Param("memberId")Long memberId);

}