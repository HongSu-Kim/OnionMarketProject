package com.youprice.onion.controller;

import com.youprice.onion.dto.product.ProductListDTO;
import com.youprice.onion.dto.product.SearchRequirements;
import com.youprice.onion.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MainController {

	private final ProductService productService;

	@GetMapping("/")
	public String main(Model model, @PageableDefault Pageable pageable) {

		SearchRequirements searchRequirements = SearchRequirements.builder()
				.blindStatus(false)
				.build();

		searchRequirements.setPageable(PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
				pageable.getPageSize(), Sort.Direction.DESC, "viewCount"));
		List<ProductListDTO> hotProductList = productService.getProductListDTO(searchRequirements).getContent();

		searchRequirements.setPageable(PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
				pageable.getPageSize(), Sort.Direction.DESC, "uploadDate"));
		List<ProductListDTO> newProductList = productService.getProductListDTO(searchRequirements).getContent();

		model.addAttribute("hotProductList", hotProductList);
		model.addAttribute("newProductList", newProductList);
		model.addAttribute("pageName", "main");
		return "main/main";
	}
}
