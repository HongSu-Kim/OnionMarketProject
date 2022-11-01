package com.youprice.onion.service.product;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.dto.product.TownAddDTO;
import com.youprice.onion.dto.product.TownFindDTO;
import com.youprice.onion.entity.product.Town;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public interface TownService {

    void townAdd(TownAddDTO townAddDTO, HttpServletResponse response,String townName)throws IOException;

  void townRangeSearch(String townName, Double range, Model model, HttpSession session, HttpServletRequest request,Long memberId);

  void  townRangeSearchGet(Model mode, HttpServletRequest request, MemberDTO memberDTO,HttpSession session,Object coordinateId,Double range);
   List<TownFindDTO> townList(Long townId);
    List<TownFindDTO> townLists(Long memberId);
}
