package com.youprice.onion.repository.product;


import com.youprice.onion.entity.product.Town;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TownRepository extends JpaRepository<Town, Long> {

    List<Town> findAllById(Long townId);


    List<Town> findAllByMemberId(Long memberId);

    Optional<Town> findByCoordinateTownName(String townName);

    Optional<Town> findByMemberIdAndCoordinateTownNameContains(Long memberId, String townNameStr);

    Optional<Town> findByCoordinateLatitude(Double Latitude);

    Optional<Town> findByCoordinateLongitude(Double Longitude);

    Optional<Town> findByCoordinateId(String townName);

    List<Town> findTownByCoordinateId(Long coordinateId);

    boolean findByCoordinateId(Long coordinateId);

    int countByMemberId(Long memberId);


}





