package com.youprice.onion.service.member;

import com.youprice.onion.dto.member.*;
import com.youprice.onion.entity.member.ProhibitionKeyword;

import java.util.List;

public interface ProhibitionKeywordService {

    void ProhibitionKeywordAdd(ProhibitionKeywordAddDTO prohibitionKeywordAddDTO);

    void ProhibitionKeywordUpdate(ProhibitionKeywordUpdateDTO prohibitionKeywordUpdateDTO,String updatekeyword);


    void  ProhibitionKeywordDelete(String prohibitionKeywordName);

    List<ProhibitionKeywordFindDTO> prohibitionKewordList();



}
