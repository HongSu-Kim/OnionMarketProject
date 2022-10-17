package com.youprice.onion.service.member;

import com.youprice.onion.dto.member.*;
import com.youprice.onion.entity.member.ProhibitionKeyword;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ProhibitionKeywordService {

    void ProhibitionKeywordAdd(ProhibitionKeywordAddDTO prohibitionKeywordAddDTO, HttpServletResponse response)throws IOException;

    void ProhibitionKeywordUpdate(ProhibitionKeywordUpdateDTO prohibitionKeywordUpdateDTO,String updatekeyword,HttpServletResponse response)throws IOException;


    void  ProhibitionKeywordDelete(String prohibitionKeywordName);

    List<ProhibitionKeywordFindDTO> prohibitionKewordList();

    boolean ProhibitionKeywordFind(String ProhibitionKeywordName );



}
