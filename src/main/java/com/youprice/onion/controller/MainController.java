package com.youprice.onion.controller;

import com.youprice.onion.dto.product.ProductListDTO;
import com.youprice.onion.dto.product.SearchRequirements;
import com.youprice.onion.entity.product.ProductProgress;
import com.youprice.onion.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MainController {

	private final ProductService productService;

	@GetMapping("/")
	public String main(Model model, @PageableDefault(size = 12) Pageable pageable) {

		SearchRequirements searchRequirements = SearchRequirements.builder()
				.productProgress(ProductProgress.SALESON)
				.blindStatus(false)
				.build();

		searchRequirements.setPageable(
				PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.DESC, "uploadDate"));
		List<ProductListDTO> newProductList = productService.getProductListDTO(searchRequirements).getContent();

		searchRequirements.setPageable(
				PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.DESC, "viewCount"));
		List<ProductListDTO> topViewProductList = productService.getProductListDTO(searchRequirements).getContent();

		searchRequirements.setPageable(
				PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.ASC, "price"));
		List<ProductListDTO> lowPriceProductList = productService.getProductListDTO(searchRequirements).getContent();

		searchRequirements.setAuctionStatus(true);
		searchRequirements.setPageable(
				PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.DESC, "uploadDate"));
		List<ProductListDTO> auctionProductList = productService.getProductListDTO(searchRequirements).getContent();


		model.addAttribute("newProductList", newProductList);
		model.addAttribute("topViewProductList", topViewProductList);
		model.addAttribute("lowPriceProductList", lowPriceProductList);
		model.addAttribute("auctionProductList", auctionProductList);
		model.addAttribute("pageName", "main");
		return "main/main";
	}
}
