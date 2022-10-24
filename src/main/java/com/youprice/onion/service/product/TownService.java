package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.TownAddDTO;
import com.youprice.onion.dto.product.TownFindDTO;
import com.youprice.onion.entity.product.Town;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface TownService {

    void townAdd(TownAddDTO townAddDTO, HttpServletResponse response)throws IOException;
   List<TownFindDTO> townList(Long townId);
    List<TownFindDTO> townLists(Long memberId);
}
