package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.CoordinateCreateDTO;
import com.youprice.onion.entity.product.Coordinate;

import java.util.List;

public interface CoordinateService {

    void coordinateCreate(CoordinateCreateDTO coordinateCreatedto);
    List<Coordinate> FindGangnam();

    List<Coordinate> FindSongpa();

    List<Coordinate> FindGangdong();

}
