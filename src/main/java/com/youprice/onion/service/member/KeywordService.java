package com.youprice.onion.service.member;

import com.youprice.onion.dto.member.KeywordCreateDTO;
import com.youprice.onion.dto.member.KeywordListDTO;
import com.youprice.onion.entity.member.Keyword;
import org.springframework.ui.Model;

import java.util.List;

public interface KeywordService {
    void KeywordCreate(KeywordCreateDTO keywordCreateDto);

    List<KeywordListDTO> KeywordList(Long memberId);

   // void KeywordAlram(String subject, String productName, Model model);





}
