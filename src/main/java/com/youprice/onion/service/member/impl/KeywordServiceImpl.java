package com.youprice.onion.service.member.impl;

import com.youprice.onion.dto.member.KeywordCreateDTO;
import com.youprice.onion.dto.member.KeywordListDTO;
import com.youprice.onion.entity.member.Keyword;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.repository.member.KeywordRepositoy;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.service.member.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
 public void KeywordCreate(KeywordCreateDTO keywordCreateDto, HttpServletResponse response) throws IOException {
  Keyword keyword = new Keyword();
  response.setContentType("text/html;charset=UTF-8");
  PrintWriter out =response.getWriter();

  if(keywordCreateDto.getKeywordName() == ""){


   out.println("<script>alert('공백입니다 키워드를 다시입력하세요');history.go(-1); </script>");
   out.flush();
   return ;
  }



  Member member = memberRepositoy.findById(keywordCreateDto.getMemberId()).orElse(null);

  keyword = keyword.keywordCreate(keywordCreateDto,member);

  if(keywordRepositoy.findByKeywordNameAndMember(keywordCreateDto.getKeywordName(),member) ==null){
   keywordRepositoy.save(keyword);
   out.println("<script>alert('등록완료!');history.go(-1); </script>");
   out.flush();
  }
  else  return;

 }


 @Override
 public List<KeywordListDTO> KeywordList(Long memberDTO) {


return keywordRepositoy.findAllByMemberId(memberDTO)
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

