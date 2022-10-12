package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.product.SearchAddDTO;
import com.youprice.onion.entity.product.Search;
import com.youprice.onion.repository.product.SearchRepositoy;
import com.youprice.onion.service.product.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService {

 private final SearchRepositoy searchRepositoy;
 private  final SearchRepositoy.Searchrepositoy searchrepositoy;

 @Override
 public void SearchCreate(SearchAddDTO searchAddDTO, String SearchName) {
    Search search = new Search();

    if(SearchName == ""){
     System.out.println("공백입니다");
     return ;
    }
 search.SearchAdd(searchAddDTO);



   searchRepositoy.save(search);
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

