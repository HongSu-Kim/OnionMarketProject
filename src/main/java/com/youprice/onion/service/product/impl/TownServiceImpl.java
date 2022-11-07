package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.product.*;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Coordinate;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.product.CoordinateRepositoy;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.repository.product.TownRepository;
import com.youprice.onion.service.product.ProductService;
import com.youprice.onion.service.product.TownService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    private final CoordinateRepositoy coordinateRepositoy;
    private final CoordinateRepositoy.Coordinaterepositoy coordinaterepositoy;
    private final MemberRepository memberRepositoy;

    @Override
    public void townAdd(TownAddDTO townAddDTO, HttpServletResponse response, String townName) throws IOException {
        com.youprice.onion.entity.product.Town town = new com.youprice.onion.entity.product.Town();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Coordinate coordinate = coordinaterepositoy.findCoordinateId(townAddDTO.getTownName());
        Member member = memberRepositoy.findById(townAddDTO.getMemberId()).orElse(null);

        coordinate = coordinateRepositoy.findById(coordinate.getId()).orElse(null);

        if (coordinate.getId() == null) {
            out.println("<script>alert('동네를 입력하세요');history.go(-3); </script>");
            out.flush();
            return;
        }

        if (townRepository.countByMemberId(member.getId()) >= 3) {
            out.println("<script>alert('가능한 동네설정개수를 초과하셨습니다(최대 3개)');history.go(-3); </script>");
            out.flush();
            return;
        }

        Optional<com.youprice.onion.entity.product.Town> DuplicatechecktopcategoryName = townRepository.findByMemberIdAndCoordinateTownNameContains(member.getId(), coordinate.getTownName());
        if (DuplicatechecktopcategoryName.isPresent()) {
            out.println("<script>alert('이미설정한 동네입니다 다시입력하세요');history.go(-3); </script>");
            out.flush();
            return;
        }

        town.townCreate(townAddDTO, coordinate, member);

        townRepository.save(town);
        out.println("<script>alert('동네 설정 완료!');history.go(-3); </script>");
        out.flush();

    }

    @Override
    public void townDelete(Long townId) {
        townRepository.deleteById(townId);

    }

    @Override
    public List<TownFindDTO> townList(Long townId) { //townId로 전체리스트 조회

        return townRepository.findAllById(townId)
                .stream().map(town -> new TownFindDTO(town))
                .collect(Collectors.toList());
    }

    @Override
    public List<TownFindDTO> townLists(Long memberId) { //memberId로 전체리스트 조회

        return townRepository.findAllByMemberId(memberId)
                .stream().map(town -> new TownFindDTO(town))
                .collect(Collectors.toList());
    }

}

