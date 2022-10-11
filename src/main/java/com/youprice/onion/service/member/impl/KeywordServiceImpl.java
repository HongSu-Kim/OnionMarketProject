package com.youprice.onion.service.member.impl;

import com.youprice.onion.dto.member.KeywordCreateDTO;
import com.youprice.onion.dto.member.KeywordListDTO;
import com.youprice.onion.dto.order.OrderProductDTO;
import com.youprice.onion.entity.member.Keyword;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.repository.member.KeywordRepositoy;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.service.member.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class KeywordServiceImpl implements KeywordService {

 private final KeywordRepositoy keywordRepositoy;
 private  final  KeywordRepositoy.Keywordrepositoy keywordrepositoy;
 private  final  MemberRepository.Memberrepositoy memberrepositoy;
 private  final  MemberRepository memberRepositoy;

 @Override
 public void KeywordCreate(KeywordCreateDTO keywordCreateDto) {
  Keyword keyword = new Keyword();



  Member member = memberRepositoy.findById(keywordCreateDto.getMemberId()).orElse(null);

  keyword = keyword.keywordCreate(keywordCreateDto,member);

  if(keywordRepositoy.findByKeywordNameAndMember(keywordCreateDto.getKeywordName(),member) ==null){
   keywordRepositoy.save(keyword);
  }
  else  return;

 }

 @Override
 public List<KeywordListDTO> KeywordList(Long memberId) {


return keywordRepositoy.findAllByMemberId(memberId)
                .stream().map(keyword -> new KeywordListDTO(keyword))
                .collect(Collectors.toList());


 }

// @Override
// public void KeywordAlram(String subject, String productName, Model model) {
//  Keyword keyword = new Keyword();
//
//
//
//  if(keywordrepositoy.keywordalram(subject,productName) !=null){
//
//  // keywordrepositoy.updatecount(subject,productName);
//   return;
//  }
//
//
//  else
//
//   return;
// }




}

