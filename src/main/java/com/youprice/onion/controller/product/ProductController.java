package com.youprice.onion.controller.product;

import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.product.*;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.product.*;
import com.youprice.onion.util.AlertRedirect;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;
    private final TownService townService;
    private final CategoryService categoryService;
    private final ProductImageService productImageService;

    @GetMapping("add")//상픔 등록 주소
    public String add(Model model, @LoginUser SessionDTO userSession, HttpServletResponse response) throws IOException {
        //Session이 없을 경우 로그인 처리
        if(userSession == null){
            AlertRedirect.warningMessage(response,"/member/login", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }

        List<TownFindDTO> townList = townService.townLists(userSession.getId());
        //동네정보가 없을 경우 등록 처리
        if(townList.size() == 0) {
            AlertRedirect.warningMessage(response, "/town/town", "내 동네를 먼저 등록해주세요.");
            return null;
        }

        List<Category> topCategory = categoryService.findTopCategory();
        List<Category> subCategory = categoryService.findSubCategory();

        model.addAttribute("townList", townList);
        model.addAttribute("topCategory", topCategory);
        model.addAttribute("subCategory", subCategory);

        return "product/addProduct";//상품등록 페이지
    }
    @PostMapping("add")//실제 상품 등록 주소
    public String addProduct(@LoginUser SessionDTO userSession, @Valid ProductAddDTO productAddDTO,
                             List<MultipartFile> fileList, Model model) throws Exception {

        /*세션아이디로 멤버아이디 set*/
        productAddDTO.setMemberId(userSession.getId());

        Long productId = productService.addProduct(productAddDTO,fileList);

        model.addAttribute("productId",productId);

        return "redirect:/product/detail?productId="+productId;//상품 상세페이지로 이동
    }

    @GetMapping("detail")//상품 상세페이지 주소
    public String detail(Long productId, Model model) throws Exception{

        productService.updateView(productId);//조회수 증가

        ProductFindDTO productFindDTO = productService.getProductFindDTO(productId);

        model.addAttribute("productId",productId);
        model.addAttribute("productFindDTO",productFindDTO);

        return "product/detail";
    }

    @GetMapping(value = "main")//상품 리스트 메인 화면 주소
    public String main(Model model) throws Exception {

        List<ProductListDTO> list = productService.getProductList();

        model.addAttribute("list",list);

        return "product/main";//상품 리스트 메인 화면페이지
    }
    @GetMapping(value = "main/category")//상품 카테고리별 화면 주소
    public String main(Model model,@RequestParam("categoryId") int categoryId ) throws Exception {

        if (categoryId == 1) {
            List<ProductListDTO> categoryList1 = productService.getProductCategoryList(1L, 8L);
            model.addAttribute("categoryList1", categoryList1);
            return "product/main";
        }


        if (categoryId == 9) {
            List<ProductListDTO> categoryList2 = productService.getProductCategoryList(9L, 11L);
            model.addAttribute("categoryList2", categoryList2);

            return "product/main";
        }

        if (categoryId == 12) {
            List<ProductListDTO> categoryList3 = productService.getProductCategoryList(12L, 16L);
            model.addAttribute("categoryList3", categoryList3);

            return "product/main";
        }


        if (categoryId == 17) {
            List<ProductListDTO> categoryList4 = productService.getProductCategoryList(17L, 26L);
            model.addAttribute("categoryList4", categoryList4);

            return "product/main";
        }


        if (categoryId == 27) {
            List<ProductListDTO> categoryList5 = productService.getProductCategoryList(27L, 41L);
            model.addAttribute("categoryList5", categoryList5);

            return "product/main";
        }


        if (categoryId == 42) {
            List<ProductListDTO> categoryList6 = productService.getProductCategoryList(42L, 56L);
            model.addAttribute("categoryList6", categoryList6);

            return "product/main";
        }


        if (categoryId == 57) {
            List<ProductListDTO> categoryList7 = productService.getProductCategoryList(57L, 60L);
            model.addAttribute("categoryList7", categoryList7);

            return "product/main";
        }


        if (categoryId == 61) {
            List<ProductListDTO> categoryList8 = productService.getProductCategoryList(61L, 70L);
            model.addAttribute("categoryList8", categoryList8);

            return "product/main";
        }


        if (categoryId == 71) {
            List<ProductListDTO> categoryList9 = productService.getProductCategoryList(71L, 85L);
            model.addAttribute("categoryList9", categoryList9);

            return "product/main";
        }


        if (categoryId == 86) {
            List<ProductListDTO> categoryList10 = productService.getProductCategoryList(86L, 89L);
            model.addAttribute("categoryList10", categoryList10);

            return "product/main";
        }


        if (categoryId == 90) {
            List<ProductListDTO> categoryList11 = productService.getProductCategoryList(90L, 95L);
            model.addAttribute("categoryList11", categoryList11);

            return "product/main";
        }

        if (categoryId == 96) {
            List<ProductListDTO> categoryList12 = productService.getProductCategoryList(96L, 104L);
            model.addAttribute("categoryList12", categoryList12);

            return "product/main";
        }

        if (categoryId == 105) {
            List<ProductListDTO> categoryList13 = productService.getProductCategoryList(105L, 113L);
            model.addAttribute("categoryList13", categoryList13);

            return "product/main";
        }


        if (categoryId == 114) {
            List<ProductListDTO> categoryList14 = productService.getProductCategoryList(114L, 115L);
            model.addAttribute("categoryList14", categoryList14);

            return "product/main";
        }


        return  "product/main";
    }


    @GetMapping(value = "update")//상품 업데이트 주소
    public String update(Model model, Long productId, @LoginUser SessionDTO userSession, HttpServletResponse response) throws IOException {

        if(userSession == null){
            AlertRedirect.warningMessage(response,"/member/login", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }

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

	// 상품상태 수정
	@GetMapping("progressUpdate")
	public String progressUpdate(Long productId, String productProgress) {
		productService.progressUpdate(productId, productProgress);
		return "redirect:/order/sellList";
	}

    @PostMapping(value = "update")//실제 상품 업데이트 주소
    public String updateProduct(Model model,Long productId, ProductUpdateDTO updateDTO, @LoginUser SessionDTO userSession,
                                 HttpServletResponse response) throws Exception{

        if(userSession == null){
            AlertRedirect.warningMessage(response,"/member/login", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }

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
    public String removeProduct(Long productId, @LoginUser SessionDTO userSession, HttpServletResponse response)
            throws Exception {

        if(userSession == null){
            AlertRedirect.warningMessage(response,"/member/login", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }

        //DB삭제가 아닌 boolean사용
        productService.deleteProduct(productId);

        return "redirect:/product/main";//삭제 후 메인 화면
    }
}
