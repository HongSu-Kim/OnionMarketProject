package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.product.CoordinateCreateDTO;
import com.youprice.onion.entity.product.Coordinate;
import com.youprice.onion.repository.product.CoordinateRepositoy;
import com.youprice.onion.service.product.CoordinateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CoordinateServiceImpl implements CoordinateService {

    private final CoordinateRepositoy coordinateRepositoy;
    private final CoordinateRepositoy.Coordinaterepositoy coordinaterepositoy;

    public void coordinateCreate(CoordinateCreateDTO coordinateCreatedto){

        Coordinate coordinate = new Coordinate();

        coordinate.coordinateCreate(coordinateCreatedto);


    coordinateRepositoy.save(coordinate);



    }

    public List<Coordinate> FindGangnam(){

        return coordinaterepositoy.findGangnam();
    }

    public List<Coordinate> FindSongpa(){

        return coordinaterepositoy.findSongpa();
    }

    public List<Coordinate> FindGangdong(){

        return coordinaterepositoy.findGangdong();
    }

}









