package com.youprice.onion.controller.product;

import com.youprice.onion.dto.product.CoordinateAddDTO;
import com.youprice.onion.dto.product.TownFindDTO;
import com.youprice.onion.service.product.CoordinateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
@RequestMapping("coordinate")
public class CoordinateController {

    private final CoordinateService coordinateService;


    @GetMapping("coordinate")
    public String createMap() {

        return "product/coordinate";

    }

    @PostMapping("coordinate")
    public String createMap(CoordinateAddDTO coordinateAddDTO, Model model) {

        coordinateService.coordinateAdd(coordinateAddDTO);


        return "redirect:/coordinate/coordinate";
    }


}
