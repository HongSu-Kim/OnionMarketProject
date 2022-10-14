package com.youprice.onion.repository.member;
import com.youprice.onion.entity.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String userId);
    boolean existsByUserId(String userId); //해당 데이터가 존재할 경우 true, 존재하지 않을 경우 false 반환
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);

    @Repository
    @RequiredArgsConstructor


    public class Memberrepositoy {


        private final EntityManager em;


        public List<Member> findMemberId(String userId) {

            return em.createQuery("select o from Member o where o.userId = :userId ", Member.class)
                    .setParameter("userId", userId).getResultList();

        }

        public Member findmember(String userId) {

            return em.createQuery("select o from Member o where o.userId = :userId ", Member.class)
                    .setParameter("userId", userId).getSingleResult();

        }



    }
}