package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.TownAddDTO;
import com.youprice.onion.dto.product.TownFindDTO;
import com.youprice.onion.entity.product.Town;

import java.util.List;

public interface TownService {

    void townAdd(TownAddDTO townAddDTO);
   List<TownFindDTO> townList(Long id);
}
