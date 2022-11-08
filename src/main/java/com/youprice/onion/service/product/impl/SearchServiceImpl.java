package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.product.SearchAddDTO;
import com.youprice.onion.dto.product.SearchFindDTO;
import com.youprice.onion.entity.product.Search;
import com.youprice.onion.repository.member.ProhibitionKeywordRepositoy;
import com.youprice.onion.repository.product.SearchRepositoy;
import com.youprice.onion.service.member.ProhibitionKeywordService;
import com.youprice.onion.service.product.SearchService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService {

    private final SearchRepositoy searchRepositoy;


    private final SearchRepositoy.Searchrepositoy searchrepositoy;

    private final ProhibitionKeywordRepositoy prohibitionKeywordRepositoy;

    private final ProhibitionKeywordService prohibitionKeywordService;

    @Override
    public void SearchCreate(SearchAddDTO searchAddDTO, String SearchName, HttpServletResponse response) throws IOException {
        Search search = new Search();

        if (SearchName == "") {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<script>alert('공백입니다 키워드를 다시입력하세요');history.go(-1); </script>");
            out.flush();
            return;
        }

        if (prohibitionKeywordService.ProhibitionKeywordFind(SearchName) == true) { //금지키워드 조건 예시

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();

            out.println("<script>alert('금지어입니다 다시입력하세요');history.go(-1); </script>");
            out.flush();


            return;
        }

        search.SearchAdd(searchAddDTO);


        searchRepositoy.save(search);
    }


//    @Override
//    public List<Search> findBySearchRank() {
//        return searchRepositoy.findAllByOrderBySearchCountDesc();
//    }

    @Override
    public List<Search> findBySearchRank5() {

        return searchRepositoy.findTop5ByOrderBySearchCountDesc();

    }

    @Override
    public List<Search> findBySearchRank10() {

        return searchRepositoy.findTop10ByOrderBySearchCountDesc();

    }

    @Override
    public List<Search> findBySearchRank20() {

        return searchRepositoy.findTop20ByOrderBySearchCountDesc();

    }

    @Override
    public Search findBySearchName(String searchName) {
        return searchRepositoy.findBySearchName(searchName);
    }

    @Override
    public int searchupdatecount(String searchName) {
        return searchrepositoy.updatecount(searchName);
    } //검색시 검색수 증가


}

