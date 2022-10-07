package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.product.SearchCreateDTO;
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



 public void SearchCreate(SearchCreateDTO searchCreateDto, String SearchName){

  Search search = new Search();

 search.SearchCreate(searchCreateDto);



   searchRepositoy.save(search);






 }

public Long Searchcount(){
  return searchrepositoy.Search();
}


 public List<Search> searchList(){

  return  searchrepositoy.findSearch();
 }


}

