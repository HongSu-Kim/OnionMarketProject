package com.youprice.onion.service.member;

import com.youprice.onion.dto.member.KeywordCreateDTO;
import com.youprice.onion.dto.member.KeywordListDTO;
import com.youprice.onion.entity.member.Keyword;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface KeywordService {
    void KeywordCreate(KeywordCreateDTO keywordCreateDto, HttpServletResponse response)throws IOException;

    List<KeywordListDTO> KeywordList(Long memberDTO);

   // void KeywordAlram(String subject, String productName, Model model);





}
