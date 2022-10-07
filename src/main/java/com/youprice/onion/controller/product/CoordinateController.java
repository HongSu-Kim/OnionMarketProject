package com.youprice.onion.controller.product;

import com.youprice.onion.dto.product.CoordinateCreateDTO;
import com.youprice.onion.dto.product.TownFindDTO;
import com.youprice.onion.service.product.impl.CoordinateServiceImpl;
import com.youprice.onion.service.product.impl.TownServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class CoordinateController {

    private  final CoordinateServiceImpl coordinateServiceImple;

    private  final TownServiceImpl townServiceImpl;


    @GetMapping("/regionalsettings")
    public String createMap(){

        return "regionalsettings";


    }

    @PostMapping("/main/mapcreate")
    public String createMap(CoordinateCreateDTO coordinateCreatedto, Model model, HttpSession session){
    TownFindDTO townFinddto = new TownFindDTO();
        coordinateServiceImple.coordinateCreate(coordinateCreatedto);



        return "index";
    }



}
