package com.youprice.onion.repository.product;


import com.youprice.onion.entity.product.Coordinate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public interface CoordinateRepositoy extends JpaRepository<Coordinate,Long> {

    List<Coordinate> findByTownNameContaining(String townName);


}
