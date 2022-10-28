package com.youprice.onion.controller.product;

import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.product.*;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.member.ProhibitionKeywordService;
import com.youprice.onion.service.product.*;
import com.youprice.onion.util.AlertRedirect;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;
    private final TownService townService;
    private final CategoryService categoryService;
    private final ProductImageService productImageService;
    private final BiddingService biddingService;
    private final ProhibitionKeywordService prohibitionKeywordService;

    @GetMapping("add")//상픔 등록 주소
    public String add(Model model, @LoginUser SessionDTO userSession, HttpServletResponse response) throws IOException {

        //Session이 없을 경우 로그인 처리
        if(userSession == null){
            AlertRedirect.warningMessage(response,"/member/login", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }

        /*세션아이디로 동네 조회*/
        List<TownFindDTO> townList = townService.townLists(userSession.getId());
        //동네정보가 없을 경우 등록 처리
        if(townList.size() == 0) {
            AlertRedirect.warningMessage(response, "/town/town", "내 동네를 먼저 등록해주세요.");
            return null;
        }

        /*카테고리 조회*/
        List<Category> topCategory = categoryService.findTopCategory();
        List<Category> subCategory = categoryService.findSubCategory();

        model.addAttribute("townList", townList);
        model.addAttribute("topCategory", topCategory);
        model.addAttribute("subCategory", subCategory);

        return "product/addProduct";//상품등록 페이지
    }
    @PostMapping("add")//실제 상품 등록 주소
    public String addProduct(@LoginUser SessionDTO userSession, ProductAddDTO productAddDTO, BindingResult bindingResult,
                             HttpServletResponse response, List<MultipartFile> fileList, Model model) throws Exception {

        if (prohibitionKeywordService.ProhibitionKeywordFind(productAddDTO.getSubject())) { //금지키워가있으면 true
            bindingResult.addError(new FieldError("productAddDTO", "subject", "적합하지 않은 단어가 포함되어 있습니다."));

            if (bindingResult.hasErrors()) {
                return "product/addProduct";
            }
        }
        if (productAddDTO.getCategoryId()==null) {
            AlertRedirect.warningMessage(response,"/product/add", "카테고리를 선택해주세요.");

            return "redirect:/product/addProduct";
        }

        /*세션아이디로 멤버아이디 set*/
        productAddDTO.setMemberId(userSession.getId());
        /*상품 등록*/
        Long productId = productService.addProduct(productAddDTO,fileList);

        model.addAttribute("productId",productId);

        return "redirect:/product/detail/"+productId;//상품 상세페이지로 이동
    }

    @GetMapping("/detail/{productId}")//상품 상세페이지 주소
    public String detail(@PathVariable("productId") Long productId, @LoginUser SessionDTO userSession, Model model)
            throws Exception{

        /*조회수 증가*/
        productService.updateView(productId);
        /*상품조회*/
        ProductFindDTO productFindDTO = productService.getProductFindDTO(productId);
        /*리뷰 조회*/
//        Double reviewAvg = reviewService.avgGrade(productFindDTO.getMemberId());
        /*입찰 리스트 조회*/
        List<BiddingListDTO> biddingList = biddingService.getBiddingList(productId, model);
        /*카테고리 상품 추천*/
        List<ProductFindDTO> categoryDTO = productService.getProductSubCategory(productId,productFindDTO.getCategoryId());

        System.out.println("productFindDTO = " + productFindDTO.getAuctionDeadline());

        model.addAttribute("userSession",userSession);
        model.addAttribute("productId",productId);
        model.addAttribute("productFindDTO",productFindDTO);
//        model.addAttribute("reviewAvg",reviewAvg);
        model.addAttribute("biddingList",biddingList);
        model.addAttribute("categoryDTO",categoryDTO);

        return "product/detail";
    }
    @GetMapping("/bid/{productId}")//상품 입찰 주소
    public String bidProduct(@PathVariable("productId") Long productId, @LoginUser SessionDTO userSession,
                             BiddingAddDTO biddingAddDTO, HttpServletResponse response,Model model) throws Exception{

        //Session이 없을 경우 로그인 처리
        if(userSession == null){
            AlertRedirect.warningMessage(response,"/member/login", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }
        biddingAddDTO.setMemberId(userSession.getId());
        biddingAddDTO.setProductId(productId);

        /*입찰 목록 생성*/
        biddingService.bidProduct(biddingAddDTO);

        model.addAttribute("productId",productId);

        return "redirect:/product/detail/"+productId;
    }
    //상품 리스트 주소
    @GetMapping(value = "list")
    public String list(@LoginUser SessionDTO userSession, Model model, @PageableDefault Pageable pageable) throws Exception {

        /*세션아이디로 동네 조회*/
        List<Long> coordinateList = null;
        List<TownFindDTO> townList = null;
        if(userSession!=null){

            townList= townService.townLists(userSession.getId());
            coordinateList = townService.townLists(userSession.getId())
                    .stream()
                    .map(TownFindDTO::getCoordinateId)
                    .collect(Collectors.toList());

        }

        SearchRequirements searchRequirements = SearchRequirements.builder()
                .coordinateIdList(coordinateList)
                .build();

        searchRequirements.setPageable(PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),Sort.Direction.DESC, "uploadDate"));

        List<ProductListDTO> list = productService.getProductListDTO(searchRequirements).getContent();

        model.addAttribute("list",list);
        model.addAttribute("townList",townList);
        return "product/list";//상품 리스트 메인 화면페이지
    }

    //상품 전체 리스트 주소
    @GetMapping("allList")
    public String allList(Model model,@PageableDefault Pageable pageable) {

        SearchRequirements searchRequirements = SearchRequirements.builder()
                .blindStatus(false)
                .build();

        searchRequirements.setPageable(PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),Sort.Direction.DESC, "uploadDate"));
        List<ProductListDTO> list = productService.getProductListDTO(searchRequirements).getContent();

        model.addAttribute("list", list);
        return "product/list";
    }

    @GetMapping("auctionList") //경매 상품 리스트
    public String auctionList(Model model,@PageableDefault Pageable pageable) throws Exception {

        List<ProductListDTO> list = productService.getProductAuctionList();

        model.addAttribute("list", list);

        return "product/auctionList";//상품 리스트 메인 화면페이지
    }

    @GetMapping(value = "main/category")//상품 카테고리별 화면 주소
    public String main(Model model,@RequestParam("categoryId") int categoryId ) throws Exception {

        if (categoryId == 1) {
            List<ProductListDTO> categoryList1 = productService.getProductCategoryList(1L, 8L);
            model.addAttribute("categoryList1", categoryList1);
            return "product/list";
        }


        if (categoryId == 9) {
            List<ProductListDTO> categoryList2 = productService.getProductCategoryList(9L, 11L);
            model.addAttribute("categoryList2", categoryList2);

            return "product/list";
        }

        if (categoryId == 12) {
            List<ProductListDTO> categoryList3 = productService.getProductCategoryList(12L, 16L);
            model.addAttribute("categoryList3", categoryList3);

            return "product/list";
        }


        if (categoryId == 17) {
            List<ProductListDTO> categoryList4 = productService.getProductCategoryList(17L, 26L);
            model.addAttribute("categoryList4", categoryList4);

            return "product/list";
        }


        if (categoryId == 27) {
            List<ProductListDTO> categoryList5 = productService.getProductCategoryList(27L, 41L);
            model.addAttribute("categoryList5", categoryList5);

            return "product/list";
        }


        if (categoryId == 42) {
            List<ProductListDTO> categoryList6 = productService.getProductCategoryList(42L, 56L);
            model.addAttribute("categoryList6", categoryList6);

            return "product/list";
        }


        if (categoryId == 57) {
            List<ProductListDTO> categoryList7 = productService.getProductCategoryList(57L, 60L);
            model.addAttribute("categoryList7", categoryList7);

            return "product/list";
        }


        if (categoryId == 61) {
            List<ProductListDTO> categoryList8 = productService.getProductCategoryList(61L, 70L);
            model.addAttribute("categoryList8", categoryList8);

            return "product/list";
        }


        if (categoryId == 71) {
            List<ProductListDTO> categoryList9 = productService.getProductCategoryList(71L, 85L);
            model.addAttribute("categoryList9", categoryList9);

            return "product/list";
        }


        if (categoryId == 86) {
            List<ProductListDTO> categoryList10 = productService.getProductCategoryList(86L, 89L);
            model.addAttribute("categoryList10", categoryList10);

            return "product/list";
        }


        if (categoryId == 90) {
            List<ProductListDTO> categoryList11 = productService.getProductCategoryList(90L, 95L);
            model.addAttribute("categoryList11", categoryList11);

            return "product/list";
        }

        if (categoryId == 96) {
            List<ProductListDTO> categoryList12 = productService.getProductCategoryList(96L, 104L);
            model.addAttribute("categoryList12", categoryList12);

            return "product/list";
        }

        if (categoryId == 105) {
            List<ProductListDTO> categoryList13 = productService.getProductCategoryList(105L, 113L);
            model.addAttribute("categoryList13", categoryList13);

            return "product/list";
        }


        if (categoryId == 114) {
            List<ProductListDTO> categoryList14 = productService.getProductCategoryList(114L, 115L);
            model.addAttribute("categoryList14", categoryList14);

            return "product/list";
        }


        return  "product/list";
    }

    @GetMapping("/update/{productId}")//상품 업데이트 주소
    public String update(Model model, @PathVariable("productId") Long productId, @LoginUser SessionDTO userSession, HttpServletResponse response) throws IOException {

        /*세션 없을 경우 로그인 처리*/
        if(userSession == null){
            AlertRedirect.warningMessage(response,"/member/login", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }
        /*세션아이디로 동네 조회*/
        List<TownFindDTO> townList = townService.townLists(userSession.getId());

        /*ProductDTO 및 ProductImageDTO 조회*/
        ProductFindDTO productFindDTO = productService.getProductFindDTO(productId);
        List<ProductImageDTO> imageList = productImageService.getProductImage(productId);

        /*카테고리 조회*/
        List<Category> topCategory = categoryService.findTopCategory();
        List<Category> subCategory = categoryService.findSubCategory();

        model.addAttribute("imageList", imageList);
        model.addAttribute("townList", townList);
        model.addAttribute("topCategory", topCategory);
        model.addAttribute("subCategory", subCategory);
        model.addAttribute("dto",productFindDTO);
        model.addAttribute("productId",productId);

        return "product/updateProduct";
    }

	// 상품상태 수정
	@GetMapping("progressUpdate/{productId}/{productProgress}/{pageNumber}")
	public String progressUpdate(@PathVariable Long productId, @PathVariable String productProgress, @PathVariable int pageNumber) {
		productService.progressUpdate(productId, productProgress);
		return "redirect:/order/sellList?page=" + pageNumber;
	}

    @PostMapping("/update/{productId}")//실제 상품 업데이트 주소
    public String updateProduct(Model model, Long productId, ProductUpdateDTO updateDTO, BindingResult bindingResult,
                                @LoginUser SessionDTO userSession, HttpServletResponse response) throws Exception{

        if (prohibitionKeywordService.ProhibitionKeywordFind(updateDTO.getSubject())) { //금지키워드가있으면 true
            bindingResult.addError(new FieldError("productAddDTO", "subject", "적합하지 않은 단어가 포함되어 있습니다."));

            if (bindingResult.hasErrors()) {
                return "product/updateProduct";
            }
        }
        /*세션 없을 경우 로그인 처리*/
        if(userSession == null){
            AlertRedirect.warningMessage(response,"/member/login", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }

        /*상품 정보 업데이트*/
        Long updateId = productService.updateProduct(productId, updateDTO);

        model.addAttribute("productId",updateId);

        return "redirect:/product/detail/"+updateId;//상품 상세페이지로 이동
    }

    @GetMapping("/delete/{productId}")//상품 삭제 주소
    public String removeProduct(@PathVariable("productId") Long productId, @LoginUser SessionDTO userSession, HttpServletResponse response)
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
