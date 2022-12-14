package com.youprice.onion.repository.product;


import com.youprice.onion.dto.product.CoordinateFindDTO;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.entity.product.Coordinate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.servlet.jsp.jstl.sql.Result;
import java.util.List;
import java.util.Optional;


public interface CoordinateRepositoy extends JpaRepository<Coordinate, Long> {

    List<Coordinate> findByTownNameContaining(String townName);

    List<Coordinate> findAllById(Long coordinateId);


    Coordinate findByLatitude(Long coordinateId);

    Coordinate findByLongitude(Long coordinateId);

    Coordinate findAllById(Object coordinateId);

    Coordinate findById(String townName);


    @Repository
    @RequiredArgsConstructor

    public class Coordinaterepositoy {
        private final EntityManager em;

        public Coordinate findCoordinateId(String townName) { //townName으로 해당 coordinateId값 조회


            return em.createQuery("select o from Coordinate o  where o.townName =:townName ", Coordinate.class)
                    .setParameter("townName", townName)
                    .getSingleResult();
        }

    }

}
