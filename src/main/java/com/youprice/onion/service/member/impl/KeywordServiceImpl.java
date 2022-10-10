package com.youprice.onion.service.member.impl;

import com.youprice.onion.dto.member.KeywordCreateDTO;
import com.youprice.onion.entity.member.Keyword;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.repository.member.KeywordRepositoy;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.service.member.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KeywordServiceImpl implements KeywordService {

 private final KeywordRepositoy keywordRepositoy;
 private  final  KeywordRepositoy.Keywordrepositoy keywordrepositoy;
 private  final  MemberRepository.Memberrepositoy memberrepositoy;
 private  final  MemberRepository memberRepositoy;

 @Override
 public void KeywordCreate(KeywordCreateDTO keywordCreateDto, String userId, String keywordName) {
  Keyword keyword = new Keyword();

  Member member = new Member();

  member = memberrepositoy.findmember(userId);

  keyword = keyword.keywordCreate(keywordCreateDto,member);

  if(keywordRepositoy.findByKeywordNameAndMember(keywordName,member) ==null){
   keywordRepositoy.save(keyword);
  }
  else  return;

 }

 @Override
 public void KeywordList(Model model, String userId) {
  List<Keyword> MykeywordList = keywordrepositoy.findKeywordList(userId);


  model.addAttribute("MykeywordList",MykeywordList);
 }

 @Override
 public void KeywordAlram(String subject, String productName, Model model) {
  Keyword keyword = new Keyword();



  if(keywordrepositoy.keywordalram(subject,productName) !=null){

  // keywordrepositoy.updatecount(subject,productName);
   return;
  }


  else

   return;
 }

 @Override
 public List<Keyword> findKeywordList(String userId) {
  return keywordrepositoy.findKeywordList(userId);

 }



}

