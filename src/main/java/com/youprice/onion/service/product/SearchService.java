package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.SearchAddDTO;
import com.youprice.onion.dto.product.SearchFindDTO;
import com.youprice.onion.entity.product.Search;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface SearchService {

    void SearchCreate(SearchAddDTO searchAddDTO, String SearchName,HttpServletResponse response) throws IOException;


    Search findBySearchName(String searchName);

  //  List<Search> findBySearchRank();

    int searchupdatecount(String searchName);

    List<Search> findBySearchRank5();

    List<Search> findBySearchRank10();

    List<Search> findBySearchRank20();


}
