package com.youprice.onion.repository.product;


import com.youprice.onion.dto.product.TownAddDTO;
import com.youprice.onion.dto.product.TownFindDTO;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.entity.product.Coordinate;
import com.youprice.onion.entity.product.Town;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Transient;
import java.util.List;
import java.util.Optional;


public interface TownRepositoy extends JpaRepository<Town,Long> {

    List<Town> findAllById(Long townId);


    List<Town> findAllByMemberId(Long memberId);

    Optional<Town> findByCoordinateTownName(String townName);

	Optional<Town> findByMemberIdAndCoordinateTownNameContains(Long memberId, String townNameStr);

    Optional<Town> findByCoordinateLatitude(Double Latitude);

    Optional<Town> findByCoordinateLongitude(Double Longitude);

    Optional<Town> findByCoordinateId(String townName);

    List<Town> findTownByCoordinateId(Long coordinateId);

    boolean findByCoordinateId(Long coordinateId);


//    @Transient
//    @Query(value ="update Town o set o.wishDistance =:wishDistance   where o.coordinate=:coordinate  ")
//    void  updatesDistance(Double wishDistance, Coordinate coordinate);


//    @Modifying
//    @Query("UPDATE Question q set q.showCount = q.showCount + 1 where q.questionId = :questionId")
//    void updateShowCount(@Param("questionId") Long questionId);

    int countByMemberId(Long memberId);
    @Repository
    @RequiredArgsConstructor
    public class Townrepositoy {


        private final EntityManager em;



        @Transactional
        public int updatWishDistance(Double range, Coordinate coordinate, Member member) {
            return em.createQuery("update Town as o set o.wishDistance =:range  where o.coordinate = :coordinate and o.member =:member")
                    .setParameter("range", range)
                    .setParameter("coordinate", coordinate)
                    .setParameter("member", member)
                    .executeUpdate();
        }



    }

}





