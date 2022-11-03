package com.youprice.onion.service.product;

import com.youprice.onion.dto.product.CoordinateAddDTO;
import com.youprice.onion.dto.product.CoordinateFindDTO;
import com.youprice.onion.dto.product.SearchRequirements;
import com.youprice.onion.entity.product.Coordinate;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface CoordinateService {

    void coordinateAdd(CoordinateAddDTO coordinateAddDTO);

    List<CoordinateFindDTO> FindGangnam();

    List<CoordinateFindDTO> FindSongpa();

    List<CoordinateFindDTO> FindGangdong();

    void coordinateSearch(String townName, Double range, Model model, HttpSession session,
                          HttpServletRequest request, Long memberId, SearchRequirements searchRequirements);

}
