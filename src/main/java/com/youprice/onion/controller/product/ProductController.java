package com.youprice.onion.controller.product;

import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.product.*;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.product.*;
import com.youprice.onion.util.AlertRedirect;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    public String addProduct(@LoginUser SessionDTO userSession, @Valid @ModelAttribute ProductAddDTO productAddDTO,
                             BindingResult bindingResult, Errors errors, List<MultipartFile> fileList,
                             Model model,HttpServletResponse response) throws Exception{
        if(userSession == null){
            AlertRedirect.warningMessage(response,"/member/login", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }
//        if (errors.hasErrors()) {
//            //상품등록 실패 시 입력 데이터 값을 유지
//            model.addAttribute("productAddDTO", productAddDTO);
//
//            //유효성 통과 못한 필드와 메시지 핸들링
//            Map<String, String> validatorResult = productService.validatorHandling(errors);
//            for (String key : validatorResult.keySet()) {
//                model.addAttribute(key, validatorResult.get(key));
//            }
//
//            //상품등록 페이지로 다시 리턴
//            return "product/add";
//        }


//        if (bindingResult.hasErrors()) {
//            return "redirect:/product/add";
//        }
        /*세션아이디로 멤버아이디 set*/
        productAddDTO.setMemberId(userSession.getId());
        System.out.println("productAddDTO = " + productAddDTO.getTownName());
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
