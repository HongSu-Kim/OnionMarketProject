package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.member.KeywordListDTO;
import com.youprice.onion.dto.product.*;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Coordinate;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.Town;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.product.CoordinateRepositoy;
import com.youprice.onion.service.product.CoordinateService;
import com.youprice.onion.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.event.PaintEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
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
    public void coordinateSearch(String townName, Double range, Model model, HttpSession session, HttpServletRequest request,
                                 Long memberId, SearchRequirements searchRequirements) {


        Coordinate coordinateId = new Coordinate();
        List<CoordinateFindDTO> coordinateFindDTOList = new ArrayList<>();
        List<Long> RangeList = new ArrayList<>();

        coordinateId = coordinaterepositoy.findCoordinateId(townName);

        Member member = memberRepository.findById(memberId).orElse(null);

        //전체 coordinate번호
        coordinateFindDTOList = coordinateRepositoy.findAll()
                .stream().map(CoordinateFindDTO::new)
                .collect(Collectors.toList());

        for (int i = 0; i < coordinateFindDTOList.size(); i++) {


            double resultX1 = coordinateId.getLatitude();
            double resultX2 = coordinateFindDTOList.get(i).getLatitude();

            double resultY1 = coordinateId.getLongitude();
            double resultY2 = coordinateFindDTOList.get(i).getLongitude();


            double DistanceX = (Math.cos(resultX1) * 6400 * 2 * 3.14 / 360) * (Math.abs(resultY1 - resultY2));
            double DistanceY = 111 * (Math.abs(resultX1 - resultX2));

            double Distance = Math.round(Math.sqrt(Math.pow(DistanceX, 2) + Math.pow(DistanceY, 2)));

            if (Distance <= range) {

                RangeList.add(0, coordinateFindDTOList.get(i).getId());


            }

        }

        searchRequirements.setCoordinateIdList(RangeList);
        Page<ProductListDTO> page = productService.getProductListDTO(searchRequirements);

        model.addAttribute("page", page);
        model.addAttribute("list", page.getContent());

        session.setAttribute("RangeList",RangeList);
        session.setAttribute("distancePage",page);
        session.setAttribute("distancelist", page.getContent());


    }
}













