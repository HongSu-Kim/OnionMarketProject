package com.youprice.onion.controller.product;

import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.dto.product.ProductImageDTO;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductImage;
import com.youprice.onion.service.product.ProductImageService;
import com.youprice.onion.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;
    private final ProductImageService productImageService;

    @GetMapping("add")//상픔 등록 주소
    public String add() {
        return "product/addproduct";//상품등록 페이지
    }
    @PostMapping("add")//실제 상품 등록 주소
    public String addProduct(Model model, ProductDTO productDTO, ProductImageDTO productImageDTO, MultipartFile file) throws Exception{

        Long productId = productService.addProduct(productDTO);

        productService.addProductImage(productImageDTO,file,productId);

        model.addAttribute("productId",productId);

        return "redirect:/product/productdetail?productId="+productId;//상품 상세페이지로 이동
    }

    @GetMapping("productdetail")//상품 상세페이지 주소
    public String detail(Model model, Long productId) throws Exception{

        Product product = productService.findById(productId).orElse(null);
        List<ProductImage> productImageList = productImageService.findByProduct_ProductId(productId);

        model.addAttribute("dto",product);
        model.addAttribute("productImageDTO",productImageList);

        return "product/productdetail";
    }

    @GetMapping(value = "productmain")//상품 리스트 메인 화면 주소
    public String main(Model model) {

        List<ProductDTO> productList = productService.getProductList();

        model.addAttribute("dto",productList);

        return "product/productmain";//상품 리스트 메인 화면페이지
    }
}
