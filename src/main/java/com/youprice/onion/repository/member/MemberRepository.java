package com.youprice.onion.repository.member;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String userId);

    Optional<Member> findById(Long id);

    Optional<Member> findByNickname(String nickname);

    List<Member> findByEmail(String email);

    int countByEmail(String email);

    boolean existsByUserId(String userId); //해당 데이터가 존재할 경우 true, 존재하지 않을 경우 false 반환

    boolean existsById(Long id);

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);


}