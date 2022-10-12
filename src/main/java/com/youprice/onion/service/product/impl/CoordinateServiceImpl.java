package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.member.KeywordListDTO;
import com.youprice.onion.dto.product.CoordinateAddDTO;
import com.youprice.onion.dto.product.CoordinateFindDTO;
import com.youprice.onion.entity.product.Coordinate;
import com.youprice.onion.repository.product.CoordinateRepositoy;
import com.youprice.onion.service.product.CoordinateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CoordinateServiceImpl implements CoordinateService {

    private final CoordinateRepositoy coordinateRepositoy;

    @Override
    public void coordinateAdd(CoordinateAddDTO coordinateAddDTO) {
        Coordinate coordinate = new Coordinate();

        coordinate.coordinateAdd(coordinateAddDTO);


    coordinateRepositoy.save(coordinate);
    }

    @Override
    public List<CoordinateFindDTO> FindGangnam() {
        return coordinateRepositoy.findByTownNameContaining("강남구")
                .stream().map(coordinate -> new CoordinateFindDTO(coordinate))
                .collect(Collectors.toList());

    }

    @Override
    public List<CoordinateFindDTO> FindSongpa() {
        return coordinateRepositoy.findByTownNameContaining("송파구")
                .stream().map(coordinate -> new CoordinateFindDTO(coordinate))
                .collect(Collectors.toList());

    }

    @Override
    public List<CoordinateFindDTO> FindGangdong() {
        return coordinateRepositoy.findByTownNameContaining("강동구")
                .stream().map(coordinate -> new CoordinateFindDTO(coordinate))
                .collect(Collectors.toList());
    }



}









