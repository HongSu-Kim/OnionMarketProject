package com.youprice.onion.controller.product;

import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.dto.product.ProductImageDTO;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.service.product.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @GetMapping("/add")
    public String add() {
        return "/addproduct";
    }
    @PostMapping(value = "/add")
    public String addProduct(Model model, ProductDTO productDTO, ProductImageDTO productImageDTO, MultipartFile file, @RequestParam("price") int price) throws Exception{

        productService.createProductDTO(productDTO, productImageDTO, file, price);

        return "/productdetail";
    }

    @GetMapping("productdetail")
    public String detail() {

        return "/productdetail";
    }

    @GetMapping(value = "productmain")
    public String main(Model model) {

        List<Product> productList = productService.findAllProductDTO();

        model.addAttribute("dto",productList);

        return "/productmain";
    }
}
