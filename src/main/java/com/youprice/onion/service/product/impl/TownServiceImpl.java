package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.product.CoordinateCreateDTO;
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

@RequiredArgsConstructor
@Service
public class TownServiceImpl implements TownService {

 private final TownRepositoy townRepositoy;
 private final  TownRepositoy.Townrepositoy townrepositoy;

 private  final CoordinateRepositoy coordinateRepositoy;
 private  final MemberRepository memberRepositoy;
 private  final  MemberRepository.Memberrepositoy memberrepositoy;

 public void townCreate(TownFindDTO townFinddto, String userId){

  Town town = new Town();
  Coordinate coordinate = new Coordinate();
  Member member = new Member();
CoordinateCreateDTO coordinateCreatedto = new CoordinateCreateDTO();

coordinate = townFinddto.getCoordinate();

member = memberrepositoy.findmember(userId);

 town.townCreate(townFinddto,coordinate,member);

  townRepositoy.save(town);


 }



 public List<Town> townfind(){




return  townrepositoy.findAll();


 }




}

