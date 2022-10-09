package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.TownFindDTO;
import com.youprice.onion.entity.product.Town;

import java.util.List;

public interface TownService {

    void townCreate(TownFindDTO townFinddto, String userId);
    List<Town> townfind();
}
