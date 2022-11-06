package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.product.*;
import com.youprice.onion.entity.product.Coordinate;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.product.CoordinateRepositoy;
import com.youprice.onion.service.product.CoordinateService;
import com.youprice.onion.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class CoordinateServiceImpl implements CoordinateService {

    private final CoordinateRepositoy coordinateRepositoy;
    private final CoordinateRepositoy.Coordinaterepositoy coordinaterepositoy;
    private final MemberRepository memberRepository;

    private final ProductService productService;

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


    @Transactional(readOnly = true)
    @Override
    public List<Long> coordinateSearch(String townName, Double range) {

        List<Long> rangeList = new ArrayList<>();

		Coordinate coordinate = coordinaterepositoy.findCoordinateId(townName);

        //전체 coordinate번호
		List<Coordinate> coordinateList = coordinateRepositoy.findAll();

        for (Coordinate coordinate1 : coordinateList) {

            double resultX1 = coordinate.getLatitude();
            double resultX2 = coordinate1.getLatitude();

            double resultY1 = coordinate.getLongitude();
            double resultY2 = coordinate1.getLongitude();

            double distanceX = (Math.cos(resultX1) * 6400 * 2 * 3.14 / 360) * (Math.abs(resultY1 - resultY2));
			double distanceY = 111 * (Math.abs(resultX1 - resultX2));

            double distance = Math.round(Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2)));

            if (distance <= range) {
				rangeList.add(0, coordinate1.getId());

            }
        }



		return rangeList;
    }
}













