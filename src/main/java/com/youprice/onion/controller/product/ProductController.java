package com.youprice.onion.controller.product;

import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.product.*;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.product.CoordinateService;
import com.youprice.onion.service.product.ProductImageService;
import com.youprice.onion.service.product.ProductService;
import com.youprice.onion.service.product.TownService;
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
    private final CoordinateService coordinateService;
    private final TownService townService;

    private final ProductImageService productImageService;

    @GetMapping("add")//상픔 등록 주소
    public String add(Model model, @LoginUser SessionDTO userSession) {

        //세션아이디로 set memberId
        List<TownFindDTO> townList = townService.townList(userSession.getId());

        model.addAttribute("townList",townList);

        return "product/addProduct";//상품등록 페이지
    }
    @PostMapping("add")//실제 상품 등록 주소
    public String addProduct(Model model, @LoginUser SessionDTO userSession, Long townId, ProductAddDTO productAddDTO, List<MultipartFile> fileList) throws Exception{

        productAddDTO.setMemberId(userSession.getId());
        productAddDTO.setTownId(townId);

        System.out.println("productAddDTO = " + productAddDTO.getTownId());

        Long productId = productService.addProduct(productAddDTO,fileList);

        model.addAttribute("productId",productId);

        return "redirect:/product/detail?productId="+productId;//상품 상세페이지로 이동
    }

    @GetMapping("detail")//상품 상세페이지 주소
    public String detail(Long productId, Model model) throws Exception{

        productService.updateView(productId);

        ProductDTO productDTO = productService.getProductDTO(productId);

        List<ProductImageDTO> productImageList = productImageService.getProductImage(productId);

        model.addAttribute("productId",productId);
        model.addAttribute("dto",productDTO);
        model.addAttribute("imageList",productImageList);

        return "product/detail";
    }

    @GetMapping(value = "main")//상품 리스트 메인 화면 주소
    public String main(Model model) throws Exception {

        List<ProductListDTO> list = productService.getProductList();

        model.addAttribute("list",list);

        return "product/main";//상품 리스트 메인 화면페이지
    }

    @GetMapping(value = "update")//상품 업데이트 주소
    public String update(Model model, Long productId) {

        ProductDTO productDTO = productService.getProductDTO(productId);
        List<ProductImageDTO> productImageList = productImageService.getProductImage(productId);

        model.addAttribute("dto",productDTO);
        model.addAttribute("imageList", productImageList);
        model.addAttribute("productId",productId);

        return "product/updateProduct";
    }

    @PostMapping(value = "update")//실제 상품 업데이트 주소
    public String updateProduct(Model model,Long productId, ProductUpdateDTO updateDTO, List<MultipartFile> fileList) throws Exception{

        Long updateId = productService.updateProduct(productId, updateDTO);

        model.addAttribute("productId",updateId);

        return "redirect:/product/detail?productId="+updateId;//상품 상세페이지로 이동
    }

    @GetMapping(value = "delete")//상품 삭제 주소
    public String removeProduct(Long productId) throws Exception {
        //DB삭제가 아닌 boolean사용
        productService.deleteProduct(productId);

        return "redirect:/product/main";//삭제 후 메인 화면
    }
}
