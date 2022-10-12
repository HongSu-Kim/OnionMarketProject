package com.youprice.onion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

	@GetMapping("test")
	public String test() {
		return "layout/shop-details";
	}
}
