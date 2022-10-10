package com.youprice.onion.controller.product;

import com.youprice.onion.dto.product.CoordinateCreateDTO;
import com.youprice.onion.dto.product.TownFindDTO;
import com.youprice.onion.service.product.CoordinateService;
import com.youprice.onion.service.product.impl.CoordinateServiceImpl;
import com.youprice.onion.service.product.impl.TownServiceImpl;
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
    public String createMap(CoordinateCreateDTO coordinateCreatedto, Model model, HttpSession session) {
        TownFindDTO townFinddto = new TownFindDTO();
        coordinateService.coordinateCreate(coordinateCreatedto);


        return "redirect:/coordinate/coordinate";
    }


}
