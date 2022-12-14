package com.youprice.onion.controller.product;

import com.youprice.onion.dto.board.AnswerFormDTO;
import com.youprice.onion.dto.board.InquiryFormDTO;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("town")
public class TownController {


    private final TownService townService;
    private final MemberService memberService;
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
    public String rangeProduct(Model model, @LoginUser SessionDTO sessionDTO, @RequestParam("range") String range) {

        if (sessionDTO == null) return "redirect:/member/login";
        MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
        List<TownFindDTO> list = townService.townLists(memberDTO.getId());

        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("list", list);
        model.addAttribute("range", range);

        return "product/town";
    }

    @GetMapping("townresult")
    public String find(Model model, @RequestParam("wishtown") String wishtown,
                       @LoginUser SessionDTO sessionDTO) {


        List<CoordinateFindDTO> townList = coordinateService.findTownList(wishtown);
        MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());

        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("townList", townList);
        model.addAttribute("wishtown", wishtown);

        return "product/townresult";
    }


    @GetMapping("searchTownresult")
    public String townResult() {

        return "redirect:/town/townresult";

    }

    @PostMapping("town")
    public String townAdd(TownAddDTO townAddDTO, @RequestParam("townName") String townName, HttpServletResponse response) throws IOException {


        townService.townAdd(townAddDTO, response, townName);
        return "redirect:/town/town";

    }

    @GetMapping("townDelete")
    public String townDelte(@RequestParam("id") Long id) {

        townService.townDelete(id);

        return "redirect:/town/town";

    }
}
