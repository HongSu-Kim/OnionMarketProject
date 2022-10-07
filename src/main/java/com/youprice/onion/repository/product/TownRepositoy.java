package com.youprice.onion.repository.product;


import com.youprice.onion.entity.product.Town;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface TownRepositoy extends JpaRepository<Town,Long> {



    List<Town> findAll();




    @Repository
    @RequiredArgsConstructor


    public class Townrepositoy {




        private final EntityManager em;


        public List<Town> findAll(){

            String jpql = "select o from Town o join fetch o.coordinate" ;

            //select m from Member m join fetch m.team
            return  em.createQuery(jpql,Town.class )
                    .getResultList();

        }

    }
}





