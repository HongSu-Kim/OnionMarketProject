package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.CoordinateAddDTO;
import com.youprice.onion.dto.product.CoordinateFindDTO;
import com.youprice.onion.entity.product.Coordinate;

import java.util.List;

public interface CoordinateService {

    void coordinateAdd(CoordinateAddDTO coordinateAddDTO);
    List<CoordinateFindDTO> FindGangnam();

    List<CoordinateFindDTO> FindSongpa();

    List<CoordinateFindDTO> FindGangdong();

}
