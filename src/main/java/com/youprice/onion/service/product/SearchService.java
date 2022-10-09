package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.SearchCreateDTO;
import com.youprice.onion.entity.product.Search;

import java.util.List;

public interface SearchService {

    void SearchCreate(SearchCreateDTO searchCreateDto, String SearchName);


    Long Searchcount();
    List<Search> searchList();

    Search findBySearchName(String searchName);

    int searchupdatecount(String searchName);


}
