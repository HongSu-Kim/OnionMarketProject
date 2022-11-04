package com.youprice.onion.repository.member;

import com.youprice.onion.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String userId);

    Optional<Member> findById(Long id);

    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByEmail(String email);

    int countByEmail(String email);

    boolean existsByUserId(String userId); //해당 데이터가 존재할 경우 true, 존재하지 않을 경우 false 반환

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);

	boolean existsById(Long targetId);

	@Query("select distinct m " +
			"from Member m " +
			"where m.id in (" +
			"	select cr.product.member.id " +
			"	from Chatroom cr " +
			"	where cr.member.id = :memberId) " +
			"or m.id in (" +
			"	select cr.member.id " +
			"	from Chatroom cr " +
			"	where cr.product.member.id = :memberId)")
	List<Member> findAllChatMember(@Param("memberId") Long memberId);

}