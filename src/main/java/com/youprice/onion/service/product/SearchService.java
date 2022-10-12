package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.SearchAddDTO;
import com.youprice.onion.entity.product.Search;

import java.util.List;

public interface SearchService {

    void SearchCreate(SearchAddDTO searchAddDTO, String SearchName);


    Search findBySearchName(String searchName);

    int searchupdatecount(String searchName);


}
