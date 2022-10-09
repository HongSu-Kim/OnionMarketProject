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