package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.member.KeywordListDTO;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.product.*;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.entity.product.Coordinate;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.Town;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.product.CoordinateRepositoy;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.repository.product.TownRepositoy;
import com.youprice.onion.service.product.TownService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.w3c.dom.ranges.Range;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TownServiceImpl implements TownService {

    private final TownRepositoy townRepositoy;
    private final TownRepositoy.Townrepositoy townrepositoy;

    private final CoordinateRepositoy coordinateRepositoy;
    private final CoordinateRepositoy.Coordinaterepositoy coordinaterepositoy;
    private final MemberRepository memberRepositoy;

    private final ProductRepository productRepository;


    @Override
    public void townAdd(TownAddDTO townAddDTO, HttpServletResponse response, String townName) throws IOException {
        Town town = new Town();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Coordinate coordinate = coordinaterepositoy.findTownNumber(townAddDTO.getTownName());
        Member member = memberRepositoy.findById(townAddDTO.getMemberId()).orElse(null);

        coordinate = coordinateRepositoy.findById(coordinate.getId()).orElse(null);

        if (coordinate.getId() == null) {
            out.println("<script>alert('동네를 입력하세요');history.go(-3); </script>");
            out.flush();
            return;
        }


        if (townRepositoy.countByMemberId(member.getId()) >= 3) {
            out.println("<script>alert('가능한 동네설정개수를 초과하셨습니다(최대 3개)');history.go(-3); </script>");
            out.flush();
            return;
        }

        Optional<Town> DuplicatechecktopcategoryName = townRepositoy.findByMemberIdAndCoordinateTownNameContains(member.getId(), coordinate.getTownName());
        if (DuplicatechecktopcategoryName.isPresent()) {
            out.println("<script>alert('이미설정한 동네입니다 다시입력하세요');history.go(-3); </script>");
            out.flush();
            return;
        }

        town.townCreate(townAddDTO, coordinate, member);

        townRepositoy.save(town);
        out.println("<script>alert('동네 설정 완료!');history.go(-3); </script>");
        out.flush();

    }


    @Override
    public void townRangeSearchGet(Model model, HttpServletRequest request, MemberDTO memberDTO,HttpSession session,
                                   Object coordinateId,Double range) {
        Town town = new Town();
        List<ProductDTO> productDTO = new ArrayList<>();
        List<Object> productDTO1 = new ArrayList<>();
        List<Object> productDTO2 = new ArrayList<>();
        List<ProductRangeDTO> productRangeDTOList = new ArrayList<>();
        List<Long> productDTO3 = new ArrayList<>();

        Coordinate coordinate = coordinateRepositoy.findAllById(coordinateId);

        Member member = memberRepositoy.findById(memberDTO.getId()).orElse(null);





        productDTO = productRepository.findAll()
                .stream().map(ProductDTO::new)
                .collect(Collectors.toList());


        for (int i = 0; i < productDTO.size(); i++) {

            double resultX1 = coordinate.getLatitude();
            double resultX2 = productDTO.get(i).getLatitude();

            double resultY1 = coordinate.getLongitude();
            double resultY2 = productDTO.get(i).getLongitude();


            double DistanceX = (Math.cos(resultX1) * 6400 * 2 * 3.14 / 360) * (Math.abs(resultY1 - resultY2));
            double DistanceY = 111 * (Math.abs(resultX1 - resultX2));

            double Distance = Math.round(Math.sqrt(Math.pow(DistanceX, 2) + Math.pow(DistanceY, 2)));



            if (Distance <=  range) {


                productRangeDTOList = productRepository.findAllById(productDTO.get(i).getProductId())
                        .stream().map(ProductRangeDTO::new)
                        .collect(Collectors.toList());

                productDTO1.add(0, productRangeDTOList.get(0).getProductId());

                productDTO1.add(1, productRangeDTOList.get(0).getSubject());

                productDTO1.add(2, productRangeDTOList.get(0).getPrice());


                productDTO2.add(0, productRangeDTOList.get(0).getRepresentativeImage());
                productDTO2.add(1, productRangeDTOList.get(0).getRepresentativeImage());
                productDTO2.add(2, productRangeDTOList.get(0).getRepresentativeImage());
            } else


                model.addAttribute("productDTO3", productDTO3);

        }

        request.setAttribute("rangeProduct",productDTO1);
        request.setAttribute("rangeProduct2",productDTO2);
        //  session.setAttribute("rangeProduct", productDTO1);
        // session.setAttribute("rangeProduct2", productDTO2);

        model.addAttribute("rangeProduct", productDTO1);
        model.addAttribute("rangeProduct2", productDTO2);


        return;
    }

    @Override
    public void townRangeSearch(String townName, Double range, Model model, HttpSession session,HttpServletRequest request,Long memberId) {
        Town town = new Town();
        List<ProductDTO> productDTO = new ArrayList<>();
        List<Object> productDTO1 = new ArrayList<>();
        List<Object> productDTO2 = new ArrayList<>();
        List<ProductRangeDTO> productRangeDTOList = new ArrayList<>();
        List<Long> productDTO3 = new ArrayList<>();

        Coordinate coordinate = coordinaterepositoy.findTownNumber(townName);
        Long coordinateId=coordinate.getId();
         System.out.println(coordinate.getId());

        Member member = memberRepositoy.findById(memberId).orElse(null);


        productDTO = productRepository.findAll()
                .stream().map(ProductDTO::new)
                .collect(Collectors.toList());


        for (int i = 0; i < productDTO.size(); i++) {

            double resultX1 = coordinate.getLatitude();
            double resultX2 = productDTO.get(i).getLatitude();

            double resultY1 = coordinate.getLongitude();
            double resultY2 = productDTO.get(i).getLongitude();


            double DistanceX = (Math.cos(resultX1) * 6400 * 2 * 3.14 / 360) * (Math.abs(resultY1 - resultY2));
            double DistanceY = 111 * (Math.abs(resultX1 - resultX2));

            double Distance = Math.round(Math.sqrt(Math.pow(DistanceX, 2) + Math.pow(DistanceY, 2)));


            if (Distance <= range) {


                productRangeDTOList = productRepository.findAllById(productDTO.get(i).getProductId())
                        .stream().map(ProductRangeDTO::new)
                        .collect(Collectors.toList());

                productDTO1.add(0, productRangeDTOList.get(0).getProductId());

                productDTO1.add(1, productRangeDTOList.get(0).getSubject());

                productDTO1.add(2, productRangeDTOList.get(0).getPrice());


                productDTO2.add(0, productRangeDTOList.get(0).getRepresentativeImage());
                productDTO2.add(1, productRangeDTOList.get(0).getRepresentativeImage());
                productDTO2.add(2, productRangeDTOList.get(0).getRepresentativeImage());
            } else


                model.addAttribute("productDTO3", productDTO3);

        }

        request.setAttribute("rangeProduct",productDTO1);
        request.setAttribute("rangeProduct2",productDTO2);
//        session.setAttribute("coordinate", coordinate);
//        session.setAttribute("range", range);
      // session.setAttribute("rangeProduct2", productDTO2);

//        request.setAttribute("coordinate",coordinate);
//        request.setAttribute("range",range);

        model.addAttribute("coordinateId", coordinateId);
        model.addAttribute("range", range);
        model.addAttribute("rangeProduct", productDTO1);
        model.addAttribute("rangeProduct2", productDTO2);




        townrepositoy.updatWishDistance(range,coordinate,member);
    return;
    }


    @Override
    public List<TownFindDTO> townList(Long townId) { //townId로 전체리스트 조회

        return townRepositoy.findAllById(townId)
                .stream().map(town -> new TownFindDTO(town))
                .collect(Collectors.toList());
    }

    @Override
    public List<TownFindDTO> townLists(Long memberId) { //memberId로 전체리스트 조회

        return townRepositoy.findAllByMemberId(memberId)
                .stream().map(town -> new TownFindDTO(town))
                .collect(Collectors.toList());
    }


}

