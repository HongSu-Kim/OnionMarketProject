package com.youprice.onion.repository.member;


import com.youprice.onion.entity.member.Keyword;
import com.youprice.onion.entity.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


public interface KeywordRepositoy extends JpaRepository<Keyword,Long> {


   public Keyword findByKeywordName(String name);
   public Keyword findByKeywordNameAndMember(String name , Member id);


    @Repository
    @RequiredArgsConstructor


    public class Keywordrepositoy {


        private final EntityManager em;


        public List<Keyword> findKeywordList(String userId) {

            return em.createQuery("select o from Keyword o join fetch o.member where o.member.userId = :userId ", Keyword.class)
                    .setParameter("userId", userId)
                    .getResultList();

        }
//
    }

}
