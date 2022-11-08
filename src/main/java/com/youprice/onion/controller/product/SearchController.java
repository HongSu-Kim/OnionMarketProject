package com.youprice.onion.controller.product;


import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.product.ProductListDTO;
import com.youprice.onion.dto.product.SearchAddDTO;
import com.youprice.onion.dto.product.SearchRequirements;
import com.youprice.onion.dto.product.TownFindDTO;
import com.youprice.onion.entity.product.Search;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.member.BlockService;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.member.ProhibitionKeywordService;
import com.youprice.onion.service.product.CategoryService;
import com.youprice.onion.service.product.ProductService;
import com.youprice.onion.service.product.SearchService;
import com.youprice.onion.util.AlertRedirect;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("search")
public class SearchController {


    private final SearchService searchService;
    private final ProductService productService;
    private final BlockService blockService;


    @GetMapping("search")
    public String SearchCreate(Model model) {


        List<Search> searchRank = searchService.findBySearchRank20();

        model.addAttribute("searchRank",searchRank);

        return "product/popularSearch";
    }

    @GetMapping("list")
    public String KeywordCreate(Model model, @Valid SearchAddDTO searchAddDTO, @RequestParam("searchName") String searchName,
                                HttpServletResponse response, @LoginUser SessionDTO sessionDTO, HttpSession session,

								@PageableDefault(size = 12, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) throws IOException {


        try {

            if(searchName ==""){
                return  "redirect:/";
            }

            if (searchService.findBySearchName(searchName) == null) {
                searchService.SearchCreate(searchAddDTO, searchName, response);
            } else {
				searchService.searchupdatecount(searchName);
			}

            List<Long> blockIdList = null;
            if (sessionDTO != null) {
                blockIdList = blockService.blockIdList(sessionDTO.getId());
            }

			SearchRequirements searchRequirements = SearchRequirements.builder()
					.pageable(pageable)
					.blindStatus(false)
                    .blockIdList(blockIdList)
                    .searchValue(searchName)
					.build();

			Long memberId = sessionDTO == null ? null : sessionDTO.getId();

            Page<ProductListDTO> page = productService.getProductListDTO(memberId, searchRequirements);

            model.addAttribute("page", page);
            model.addAttribute("searchName", searchName);
            System.out.println(searchName);
            return "product/searchlist";

        } catch (RuntimeException e) {
            return AlertRedirect.warningMessage(response, "검색중 오류입니다\n");

        }


    }


}
