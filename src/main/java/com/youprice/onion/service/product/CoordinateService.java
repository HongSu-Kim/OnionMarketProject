package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.CoordinateAddDTO;
import com.youprice.onion.dto.product.CoordinateFindDTO;

import java.util.List;

public interface CoordinateService {

    void coordinateAdd(CoordinateAddDTO coordinateAddDTO);

    List<CoordinateFindDTO> findTownList(String wishtown);

    List<Long> coordinateSearch(String townName, Double range);

}
