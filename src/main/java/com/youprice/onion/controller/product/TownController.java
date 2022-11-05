package com.youprice.onion.controller.product;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.product.CoordinateFindDTO;
import com.youprice.onion.dto.product.TownAddDTO;
import com.youprice.onion.dto.product.TownFindDTO;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.entity.product.Coordinate;
import com.youprice.onion.entity.product.Town;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.member.impl.MemberServiceImpl;
import com.youprice.onion.service.product.CategoryService;
import com.youprice.onion.service.product.CoordinateService;
import com.youprice.onion.service.product.TownService;
import com.youprice.onion.service.product.impl.CategoryServiceImpl;
import com.youprice.onion.service.product.impl.CoordinateServiceImpl;
import com.youprice.onion.service.product.impl.TownServiceImpl;
import com.youprice.onion.util.AlertRedirect;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("town")
public class TownController {


    private final TownService townService;
    private final MemberService memberService;
    private final CategoryService categoryService;
    private final CoordinateService coordinateService;

    @GetMapping("town")
    public String find(Model model, @LoginUser SessionDTO sessionDTO) {

        if (sessionDTO == null) return "redirect:/member/login";
        MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
        List<TownFindDTO> list = townService.townLists(memberDTO.getId());


        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("list", list);

        return "product/town";
    }

    @PostMapping("rangeProduct")
    public String rangeProduct(Model model, @LoginUser SessionDTO sessionDTO, @RequestParam("range") String range
            , @RequestParam("townName") String townName) {

        if (sessionDTO == null) return "redirect:/member/login";
        MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
        List<TownFindDTO> list = townService.townLists(memberDTO.getId());

        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("list", list);
        model.addAttribute("range", range);

        System.out.println(range); //거리범위
        System.out.println(townName); //동네이름


        return "product/town";
    }


    @GetMapping("townresult")
    public String find(Town town, Model model, @RequestParam("wishtown") String wishtown,
                       @LoginUser SessionDTO sessionDTO) {


        List<CoordinateFindDTO> Gangnam = coordinateService.FindGangnam();
        List<CoordinateFindDTO> Songpa = coordinateService.FindSongpa();
        List<CoordinateFindDTO> Gangdong = coordinateService.FindGangdong();
        MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());

        model.addAttribute("memberDTO", memberDTO);

        model.addAttribute("Gangnam", Gangnam);
        model.addAttribute("Songpa", Songpa);
        model.addAttribute("Gangdong", Gangdong);
        model.addAttribute("wishtown", wishtown);


        return "product/townresult";
    }


    @PostMapping("townresult")
    public String townResult(Town town, Model model, @RequestParam("wishtown") String wishtown,
                             @LoginUser SessionDTO sessionDTO, HttpServletResponse response) throws IOException {
        try {

            List<CoordinateFindDTO> Gangnam = coordinateService.FindGangnam();
            List<CoordinateFindDTO> Songpa = coordinateService.FindSongpa();
            List<CoordinateFindDTO> Gangdong = coordinateService.FindGangdong();
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());

            model.addAttribute("memberDTO", memberDTO);

            model.addAttribute("Gangnam", Gangnam);
            model.addAttribute("Songpa", Songpa);
            model.addAttribute("Gangdong", Gangdong);
            model.addAttribute("wishtown", wishtown);

            return "product/townresult";

        } catch (RuntimeException e) {
            return AlertRedirect.warningMessage(response, "실패 ");
        }


    }

    @PostMapping("town")
    public String townAdd(Model model, TownAddDTO townAddDTO, @RequestParam("townName") String townName, HttpServletResponse response) throws IOException {


        townService.townAdd(townAddDTO, response, townName);
        return "redirect:/town/town";


    }
}
