package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.member.KeywordListDTO;
import com.youprice.onion.dto.product.CoordinateAddDTO;
import com.youprice.onion.dto.product.TownAddDTO;
import com.youprice.onion.dto.product.TownFindDTO;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.entity.product.Coordinate;
import com.youprice.onion.entity.product.Town;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.product.CoordinateRepositoy;
import com.youprice.onion.repository.product.TownRepositoy;
import com.youprice.onion.service.product.TownService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TownServiceImpl implements TownService {

 private final TownRepositoy townRepositoy;
 private  final CoordinateRepositoy coordinateRepositoy;
 private  final MemberRepository memberRepositoy;


 @Override
 public void townAdd(TownAddDTO townAddDTO, HttpServletResponse response)throws IOException {
  Town town = new Town();
  response.setContentType("text/html;charset=UTF-8");
  PrintWriter out =response.getWriter();

  if(townAddDTO.getCoordinateId() ==null){
   out.println("<script>alert('동네를 입력하세요');history.go(-2); </script>");
   out.flush();
   return;
  }


  Member member = memberRepositoy.findById(townAddDTO.getMemberId()).orElse(null);
  Coordinate coordinate = coordinateRepositoy.findById(townAddDTO.getCoordinateId()).orElse(null);



  if(townRepositoy.countByMemberId(member.getId())>=3){
   out.println("<script>alert('가능한 동네설정개수를 초과하셨습니다(최대 3개)');history.go(-2); </script>");
   out.flush();
   return ;
  }


  Optional<Town> DuplicatechecktopcategoryName = townRepositoy.findByMemberIdAndCoordinateTownNameContains(member.getId(), coordinate.getTownName());
  if (DuplicatechecktopcategoryName.isPresent()) {
   out.println("<script>alert('이미설정한 동네입니다 다시입력하세요');history.go(-2); </script>");
   out.flush();
   return;
  }

// if(townRepositoy.findByCoordinateId(coordinate.getId())==false) {
//  out.println("<script>alert('없는 동네번호입니다!');history.go(-2); </script>");
//  out.flush();
//  return;
// }
  town.townCreate(townAddDTO,coordinate,member);

  townRepositoy.save(town);
  out.println("<script>alert('동네 설정 완료!');history.go(-2); </script>");
  out.flush();
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

