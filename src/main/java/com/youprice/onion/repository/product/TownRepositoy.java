package com.youprice.onion.repository.product;


import com.youprice.onion.entity.product.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TownRepositoy extends JpaRepository<Town,Long> {



    List<Town> findAllById(Long townId);

    List<Town> findAllByMemberId(Long memberId);

    Optional<Town> findByCoordinateTownName(String townName);

//

}





