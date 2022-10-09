package com.youprice.onion.repository.product;


import com.youprice.onion.entity.product.Coordinate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


public interface CoordinateRepositoy extends JpaRepository<Coordinate,Long> {





    @Repository
    @RequiredArgsConstructor


    public class Coordinaterepositoy {


        private final EntityManager em;


        public List<Coordinate> findGangnam(){

            return em.createQuery("select o from Coordinate o where o.townName like '%강남구%' ", Coordinate.class)
                    .getResultList();

        }

        public List<Coordinate> findSongpa(){

            return em.createQuery("select o from Coordinate o where o.townName like '%송파구%' ", Coordinate.class)
                    .getResultList();

        }

        public List<Coordinate> findGangdong(){

            return em.createQuery("select o from Coordinate o where o.townName like '%강동구%' ", Coordinate.class)
                    .getResultList();

        }

    }


}
