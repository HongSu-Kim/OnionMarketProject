package com.youprice.onion.controller.product;

import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.product.*;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;
    private final TownService townService;
    private final CategoryService categoryService;
    private final ProductImageService productImageService;
//    private final ProductValidator productValidator;

    @GetMapping("add")//상픔 등록 주소
    public String add(Model model, @LoginUser SessionDTO userSession) {

        if(userSession == null) {
            return "redirect:/member/login";
        }

        List<TownFindDTO> townList = townService.townLists(userSession.getId());
        String townName = townList.get(0).getTownName();

        List<Category> topCategory = categoryService.findTopCategory();
        List<Category> subCategory = categoryService.findSubCategory();

        model.addAttribute("townName", townName);
        model.addAttribute("topCategory", topCategory);
        model.addAttribute("subCategory", subCategory);

        return "product/addProduct";//상품등록 페이지
    }
    @PostMapping("add")//실제 상품 등록 주소
    public String addProduct(@LoginUser SessionDTO userSession, @Valid @ModelAttribute ProductAddDTO productAddDTO,
                             BindingResult bindingResult,List<MultipartFile> fileList, Model model) throws Exception{
        //재혁이형,



        if (bindingResult.hasErrors()) {
            return "product/addProduct";
        }

        /*세션아이디로 멤버아이디 set*/
        productAddDTO.setMemberId(userSession.getId());
        /*동네 이름으로 동네번호 조회 및 set*/
        TownFindDTO townFindDTO = productService.findTownId(productAddDTO.getTownName());
        productAddDTO.setTownId(townFindDTO.getId());
        /*카테고리번호 set*/
        productAddDTO.setCategoryId(productAddDTO.getCategoryId());

        Long productId = productService.addProduct(productAddDTO,fileList);

        model.addAttribute("productId",productId);

        return "redirect:/product/detail?productId="+productId;//상품 상세페이지로 이동
    }

    @GetMapping("detail")//상품 상세페이지 주소
    public String detail(Long productId, Model model) throws Exception{

        productService.updateView(productId);//조회수 증가

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
    public String update(Model model, Long productId, @LoginUser SessionDTO userSession) {

        ProductDTO productDTO = productService.getProductDTO(productId);
        List<ProductImageDTO> imageList = productImageService.getProductImage(productId);

        List<TownFindDTO> townList = townService.townLists(userSession.getId());
        String townName = townList.get(0).getTownName();

        List<Category> topCategory = categoryService.findTopCategory();
        List<Category> subCategory = categoryService.findSubCategory();

        model.addAttribute("imageList", imageList);
        model.addAttribute("townName", townName);
        model.addAttribute("topCategory", topCategory);
        model.addAttribute("subCategory", subCategory);
        model.addAttribute("dto",productDTO);
        model.addAttribute("productId",productId);

        return "product/updateProduct";
    }

    @PostMapping(value = "update")//실제 상품 업데이트 주소
    public String updateProduct(Model model,Long productId, ProductUpdateDTO updateDTO) throws Exception{

        /*동네 이름으로 동네번호 조회 및 set*/
        TownFindDTO townFindDTO = productService.findTownId(updateDTO.getTownName());
        updateDTO.setTownId(townFindDTO.getId());
        /*카테고리번호 set*/
        updateDTO.setCategoryId(updateDTO.getCategoryId());

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
