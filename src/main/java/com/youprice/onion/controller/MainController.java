package com.youprice.onion.controller;

import com.youprice.onion.dto.product.ProductListDTO;
import com.youprice.onion.dto.product.SearchRequirements;
import com.youprice.onion.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class MainController {

	private final ProductService productService;

	@GetMapping("/")
	public String main(Model model, @PageableDefault Pageable pageable) {

		SearchRequirements allProduct = SearchRequirements.builder()
				.pageable(pageable)
				.blindStatus(false)
				.build();

		Page<ProductListDTO> page = productService.getProductListDTO(allProduct);

		model.addAttribute("page", page);
		model.addAttribute("pageName", "main");
		return "main/main";
	}
}
