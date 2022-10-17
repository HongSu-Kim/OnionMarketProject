package com.youprice.onion.repository.member;
import com.youprice.onion.entity.member.Block;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {

    boolean existsBlockByMemberAndTarget(Long member, Long target);

    @RequiredArgsConstructor
    public class Blockrepository {

        private final EntityManager em;

        public List<Block> findBlockId(String userId) {

            return em.createQuery("select o from Block o where o.userId = :userId ", Block.class)
                    .setParameter("userId", userId).getResultList();

        }

        public Block findblock(String userId) {

            return em.createQuery("select o from Block o where o.userId = :userId ", Block.class)
                    .setParameter("userId", userId).getSingleResult();

        }



    }
}