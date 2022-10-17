package com.youprice.onion.service.member.impl;


import com.youprice.onion.dto.member.*;
import com.youprice.onion.dto.product.CategoryFindDTO;
import com.youprice.onion.entity.member.Keyword;
import com.youprice.onion.entity.member.ProhibitionKeyword;
import com.youprice.onion.repository.member.ProhibitionKeywordRepositoy;
import com.youprice.onion.service.member.ProhibitionKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Transactional

public class ProhibitionKeywordServiceImpl implements ProhibitionKeywordService {

    private final ProhibitionKeywordRepositoy prohibitionKeywordRepositoy;

    @Override

    public void ProhibitionKeywordAdd(ProhibitionKeywordAddDTO prohibitionKeywordAddDTO, HttpServletResponse response) throws IOException {

        ProhibitionKeyword prohibitionKeyword = new ProhibitionKeyword();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Optional<ProhibitionKeyword> DuplicatecheckProhibitionKeywordName =
                prohibitionKeywordRepositoy.findByProhibitionKeywordName(prohibitionKeywordAddDTO.getProhibitionKeywordName());
        if (DuplicatecheckProhibitionKeywordName.isPresent()) {
            out.println("<script>alert('이미 존재하는 금지키워드입니다 다시입력하세요!');history.go(-1); </script>");
            out.flush();
            return;
        }

        if (prohibitionKeywordAddDTO.getProhibitionKeywordName() == "") {


            out.println("<script>alert('공백입니다 금지키워드를 다시입력하세요');history.go(-1); </script>");
            out.flush();
            return;
        } else

            prohibitionKeyword.prohibitionKeywordAdd(prohibitionKeywordAddDTO);

        prohibitionKeywordRepositoy.save(prohibitionKeyword);
        out.println("<script>alert('금지 키워드 등록완료!');history.go(-1); </script>");
        out.flush();
    }

    @Override
    @Transactional
    public void ProhibitionKeywordUpdate(ProhibitionKeywordUpdateDTO prohibitionKeywordUpdateDTO, String updatekeyword,HttpServletResponse response)throws  IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ProhibitionKeyword prohibitionKeyword = new ProhibitionKeyword();

        Optional<ProhibitionKeyword> DuplicatecheckProhibitionKeywordName =
                prohibitionKeywordRepositoy.findByProhibitionKeywordName(prohibitionKeywordUpdateDTO.getProhibitionKeywordName());
        if (DuplicatecheckProhibitionKeywordName.isPresent()) {
            out.println("<script>alert('이미 존재하는 금지키워드입니다. 금지키워드를 다시입력하세요');history.go(-1); </script>");
            out.flush();
            return;
        }

        if(prohibitionKeywordUpdateDTO.getProhibitionKeywordName()==""){
            out.println("<script>alert('공백입니다 금지키워드를 다시입력하세요');history.go(-1); </script>");
            out.flush();

            return;

        }

        else

            prohibitionKeywordRepositoy.deleteByProhibitionKeywordName(updatekeyword);

        prohibitionKeyword.prohibitionKeywordUpdate(prohibitionKeywordUpdateDTO);

        prohibitionKeywordRepositoy.save(prohibitionKeyword);
        out.println("<script>alert('금지키워드를 수정완료!');history.go(-1); </script>");
        out.flush();

    }

    @Override
    public void ProhibitionKeywordDelete(String prohibitionKeywordName) {
        System.out.println(prohibitionKeywordName);
        prohibitionKeywordRepositoy.deleteByProhibitionKeywordName(prohibitionKeywordName);

    }


    @Override
    public List<ProhibitionKeywordFindDTO> prohibitionKewordList() {
        return prohibitionKeywordRepositoy.findAll()
                .stream().map(ProhibitionKeywordFindDTO::new)
                .collect(Collectors.toList());

    }

    @Override
    public boolean ProhibitionKeywordFind(String ProhibitionKeywordName) { //금지어 체크 메소드
        prohibitionKeywordRepositoy.findAllByProhibitionKeywordName(ProhibitionKeywordName);
        System.out.println("금지어입니다");
        return false;
    }
}

