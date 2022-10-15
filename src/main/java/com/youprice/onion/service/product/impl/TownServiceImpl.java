package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.member.KeywordListDTO;
import com.youprice.onion.dto.product.CoordinateAddDTO;
import com.youprice.onion.dto.product.TownAddDTO;
import com.youprice.onion.dto.product.TownFindDTO;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Coordinate;
import com.youprice.onion.entity.product.Town;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.product.CoordinateRepositoy;
import com.youprice.onion.repository.product.TownRepositoy;
import com.youprice.onion.service.product.TownService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TownServiceImpl implements TownService {

 private final TownRepositoy townRepositoy;
 private  final CoordinateRepositoy coordinateRepositoy;
 private  final MemberRepository memberRepositoy;


 @Override
 public void townAdd(TownAddDTO townAddDTO) {
  Town town = new Town();

  Member member = memberRepositoy.findById(townAddDTO.getMemberId()).orElse(null);
  Coordinate coordinate = coordinateRepositoy.findById(townAddDTO.getCoordinateId()).orElse(null);

  town.townCreate(townAddDTO,coordinate,member);

  townRepositoy.save(town);
 }

 @Override
 public List<TownFindDTO> townList(Long id) { //townId로 찾기

  return townRepositoy.findAllById(id)
          .stream().map(town -> new TownFindDTO(town))
          .collect(Collectors.toList());
 }





}

