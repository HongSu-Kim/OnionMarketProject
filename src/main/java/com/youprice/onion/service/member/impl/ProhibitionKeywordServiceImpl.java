package com.youprice.onion.service.member.impl;


import com.youprice.onion.dto.member.*;
import com.youprice.onion.dto.product.CategoryFindDTO;
import com.youprice.onion.entity.member.ProhibitionKeyword;
import com.youprice.onion.repository.member.ProhibitionKeywordRepositoy;
import com.youprice.onion.service.member.ProhibitionKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Transactional

public class ProhibitionKeywordServiceImpl implements ProhibitionKeywordService {

    private  final ProhibitionKeywordRepositoy prohibitionKeywordRepositoy;

    @Override

    public void ProhibitionKeywordAdd(ProhibitionKeywordAddDTO prohibitionKeywordAddDTO) {

        ProhibitionKeyword prohibitionKeyword = new ProhibitionKeyword();

        Optional<ProhibitionKeyword> DuplicatecheckProhibitionKeywordName =
                prohibitionKeywordRepositoy.findByProhibitionKeywordName(prohibitionKeywordAddDTO.getProhibitionKeywordName());
        if(DuplicatecheckProhibitionKeywordName.isPresent()){
            return;
        }
        else

        prohibitionKeyword.prohibitionKeywordAdd(prohibitionKeywordAddDTO);

        prohibitionKeywordRepositoy.save(prohibitionKeyword);

    }

    @Override
    @Transactional
    public void ProhibitionKeywordUpdate(ProhibitionKeywordUpdateDTO prohibitionKeywordUpdateDTO,String updatekeyword) {

        ProhibitionKeyword prohibitionKeyword = new ProhibitionKeyword();

        Optional<ProhibitionKeyword> DuplicatecheckProhibitionKeywordName =
                prohibitionKeywordRepositoy.findByProhibitionKeywordName(prohibitionKeywordUpdateDTO.getProhibitionKeywordName());
        if(DuplicatecheckProhibitionKeywordName.isPresent()){
            return;
        }
        else

            prohibitionKeywordRepositoy.deleteByProhibitionKeywordName(updatekeyword);

            prohibitionKeyword.prohibitionKeywordUpdate(prohibitionKeywordUpdateDTO);

        prohibitionKeywordRepositoy.save(prohibitionKeyword);


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


}

