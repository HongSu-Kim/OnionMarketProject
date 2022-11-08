package com.youprice.onion.controller;

import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.product.CategoryFindDTO;
import com.youprice.onion.dto.product.ProductListDTO;
import com.youprice.onion.dto.product.SearchFindDTO;
import com.youprice.onion.dto.product.SearchRequirements;
import com.youprice.onion.entity.product.ProductProgress;
import com.youprice.onion.entity.product.Search;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.member.BlockService;
import com.youprice.onion.service.product.CategoryService;
import com.youprice.onion.service.product.ProductService;
import com.youprice.onion.service.product.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MainController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BlockService blockService;

    private final SearchService searchService;

    @GetMapping("/")
    public String main(@LoginUser SessionDTO sessionDTO, Model model, @PageableDefault(size = 12) Pageable pageable, HttpSession session) {

        List<Long> blockIdList = null;
        if (sessionDTO != null) {
            blockIdList = blockService.blockIdList(sessionDTO.getId());
        }

        SearchRequirements searchRequirements = SearchRequirements.builder()
                .productProgress(ProductProgress.SALESON)
                .blockIdList(blockIdList)
                .blindStatus(false)
                .build();

        Long memberId = sessionDTO == null ? null : sessionDTO.getId();

        searchRequirements.setPageable(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.DESC, "uploadDate"));
        List<ProductListDTO> newProductList = productService.getProductListDTO(memberId, searchRequirements).getContent();

        searchRequirements.setPageable(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.DESC, "viewCount"));
        List<ProductListDTO> topViewProductList = productService.getProductListDTO(memberId, searchRequirements).getContent();

        searchRequirements.setPageable(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "price"));
        List<ProductListDTO> lowPriceProductList = productService.getProductListDTO(memberId, searchRequirements).getContent();

        searchRequirements.setAuctionStatus(true);
        searchRequirements.setPageable(
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.DESC, "uploadDate"));
        List<ProductListDTO> auctionProductList = productService.getProductListDTO(memberId, searchRequirements).getContent();

        model.addAttribute("newProductList", newProductList);
        model.addAttribute("topViewProductList", topViewProductList);
        model.addAttribute("lowPriceProductList", lowPriceProductList);
        model.addAttribute("auctionProductList", auctionProductList);
        model.addAttribute("pageName", "main");

        List<CategoryFindDTO> topCategory = categoryService.findTopCategory();
        session.setAttribute("topCategory", topCategory);

        List<Search> searchRank = searchService.findBySearchRank5();

        model.addAttribute("searchRank",searchRank);
        model.addAttribute("searchRank",searchRank);




        return "main/mainPage";
    }

	//접근 거부 페이지
	@GetMapping("/main/denied")
	public String deniedView() {
		return "redirect:/";
	}

}
