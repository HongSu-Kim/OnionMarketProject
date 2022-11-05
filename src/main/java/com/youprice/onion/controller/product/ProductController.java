package com.youprice.onion.controller.product;

import com.youprice.onion.dto.chat.ChatDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.product.*;
import com.youprice.onion.entity.product.Category;
import com.youprice.onion.entity.product.ProductProgress;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.chat.ChatService;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.member.ProhibitionKeywordService;
import com.youprice.onion.service.product.*;
import com.youprice.onion.util.AlertRedirect;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;
    private final TownService townService;
    private final CategoryService categoryService;
    private final ProductImageService productImageService;
    private final CoordinateService coordinateService;
    private final BiddingService biddingService;
    private final MemberService memberService;
    private final ProhibitionKeywordService prohibitionKeywordService;
	private final ChatService chatService;
	private final SimpMessagingTemplate template;

    @GetMapping("add")//상픔 등록 주소
    public String add(Model model, @LoginUser SessionDTO userSession, HttpServletResponse response) throws IOException {

        //Session이 없을 경우 로그인 처리
        if (userSession == null) {
            AlertRedirect.warningMessage(response, "/member/login", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }

        /*세션아이디로 동네 조회*/
        List<TownFindDTO> townList = townService.townLists(userSession.getId());
        //동네정보가 없을 경우 등록 처리
        if (townList.size() == 0) {
            AlertRedirect.warningMessage(response, "/town/town", "내 동네를 먼저 등록해주세요.");
            return null;
        }

        /*카테고리 조회*/
        List<Category> topCategory = categoryService.findTopCategory();
        model.addAttribute("townList", townList);
        model.addAttribute("topCategory", topCategory);


        return "product/addProduct";//상품등록 페이지
    }

    @PostMapping("add")//실제 상품 등록 주소
    public String addProduct(@LoginUser SessionDTO userSession, ProductAddDTO productAddDTO, BindingResult bindingResult,
                             HttpServletResponse response, List<MultipartFile> fileList, Model model) throws Exception {

        if (prohibitionKeywordService.ProhibitionKeywordFind(productAddDTO.getSubject())) { //금지키워드가있으면 true
            bindingResult.addError(new FieldError("productAddDTO", "subject", "적합하지 않은 단어가 포함되어 있습니다."));

            if (bindingResult.hasErrors()) {
                return "product/addProduct";
            }
        }
        if (productAddDTO.getCategoryId() == null) {
            AlertRedirect.warningMessage(response, "/product/add", "카테고리를 선택해주세요.");

            return "redirect:/product/addProduct";
        }
        /*세션아이디로 멤버아이디 set*/
        productAddDTO.setMemberId(userSession.getId());
        /*상품 등록*/
        Long productId = productService.addProduct(productAddDTO, fileList);

        // 키워드 알림
        List<ChatDTO> chatDTOList = chatService.alertChat(productId, productAddDTO.getSubject());
        for (ChatDTO chatDTO : chatDTOList) {
          template.convertAndSend("/sub/chat/" + chatDTO.getTargetId(), chatDTO);
        }

        model.addAttribute("productId", productId);

        return "redirect:/product/detail/" + productId;//상품 상세페이지로 이동
    }

    @GetMapping("/detail/{productId}")//상품 상세페이지 주소
    public String detail(@PathVariable("productId") Long productId, @LoginUser SessionDTO userSession, Model model,
                         HttpServletResponse response) throws Exception {

        /*조회수 증가*/
        productService.updateView(productId);
        /*상품조회 및 접근 제한*/
        ProductFindDTO productFindDTO = productService.getProductFindDTO(productId);
        if (productFindDTO.getProductProgress() == ProductProgress.BLIND) {
            AlertRedirect.warningMessage(response, "/product/wishRangeList", "신고된 상품입니다.");

            return "redirect:/product/wishRangeList";
        }
        /*리뷰 조회*/
        Double reviewAvg = null;
        if (memberService.avgGrade(productFindDTO.getMemberId()) != null) {
            reviewAvg = memberService.avgGrade(productFindDTO.getMemberId());
        }
        /*입찰 리스트 조회 및 마지막 입찰가 조회*/
        List<BiddingListDTO> biddingList = biddingService.getBiddingList(productId, model);
        if (biddingList.size() > 0) {
            int bid = biddingList.get(0).getBid();

            model.addAttribute("bid", bid);
        }

        /*동일 카테고리 추천상품 조회*/
        List<ProductFindDTO> categoryDTO = productService.getProductSubCategory(productId, productFindDTO.getCategoryId());

        model.addAttribute("userSession", userSession);
        model.addAttribute("productId", productId);
        model.addAttribute("productFindDTO", productFindDTO);
        model.addAttribute("reviewAvg", reviewAvg);
        model.addAttribute("biddingList", biddingList);
        model.addAttribute("categoryDTO", categoryDTO);

        return "product/detail";
    }

    @PostMapping("bid")//상품 입찰 주소
    public String bidProduct(@LoginUser SessionDTO userSession, BiddingAddDTO biddingAddDTO,
                             HttpServletResponse response, Model model) throws Exception {

        //Session이 없을 경우 로그인 처리
        if (userSession == null) {
            AlertRedirect.warningMessage(response, "/member/login", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }

        biddingAddDTO.setMemberId(userSession.getId());
        Long productId = biddingAddDTO.getProductId();
        /*입찰 목록 생성*/
        biddingService.bidProduct(biddingAddDTO);

        model.addAttribute("productId", productId);
        model.addAttribute("bid", biddingAddDTO.getBid());

        return "redirect:/product/detail/" + productId;
    }

    //상품 동네리스트 주소
    @GetMapping(value = "wishRangeList")
    public String list(@LoginUser SessionDTO userSession, HttpSession session, Model model,
                       @PageableDefault(size = 12, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {

        /*세션아이디로 동네 조회*/
        List<Long> coordinateList = null;
		Long memberId = null;

        if (userSession != null) {

            coordinateList = townService.townLists(userSession.getId())
                    .stream()
                    .map(TownFindDTO::getCoordinateId)
                    .collect(Collectors.toList());

			memberId = userSession.getId();
        }
        SearchRequirements searchRequirements = SearchRequirements.builder()
                .blindStatus(false)
                .coordinateIdList(coordinateList)
                .build();

        searchRequirements.setPageable(pageable);
        searchRequirements.setCoordinateIdList((List<Long>) session.getAttribute("RangeList"));

        Page<ProductListDTO> distancePage = productService.getProductListDTO(memberId, searchRequirements);

        model.addAttribute("distancePage", distancePage);
        model.addAttribute("distancePagelist", distancePage.getContent());

        return "product/wishRangeList";
    }

    @PostMapping("wishRangeList")
    public String allList(Model model, @PageableDefault(size = 12, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam("range") Double range
            , @RequestParam("townName") String townName, HttpSession session, HttpServletRequest request, @RequestParam("memberId") Long memberId) {


        SearchRequirements searchRequirements = SearchRequirements.builder()
                .blindStatus(false)
                .build();

        searchRequirements.setPageable(PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(), Sort.Direction.DESC, "uploadDate"));

        coordinateService.coordinateSearch(townName, range, model, session, request, memberId, searchRequirements);


        return "product/wishRangeList";

    }

    @GetMapping("auctionList") //경매 상품 리스트
    public String auctionList(@LoginUser SessionDTO userSession, HttpSession session, Model model,
                              @PageableDefault(size = 12, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {

        SearchRequirements searchRequirements = SearchRequirements.builder()
                .blindStatus(false)
                .auctionStatus(true)
                .build();

//        if(userSession != null) {
//            searchRequirements.setMemberId(userSession.getId());
//        }
		Long memberId = null;
		if (userSession != null) {
			memberId = userSession.getId();
		}

        searchRequirements.setPageable(pageable);

        Page<ProductListDTO> page = productService.getProductListDTO(memberId, searchRequirements);

        model.addAttribute("page", page);
        model.addAttribute("list", page.getContent());

        return "product/auctionList";//상품 리스트 메인 화면페이지
    }

    @GetMapping(value = "category")//상품 카테고리별 화면 주소
    public String main(Model model, @LoginUser SessionDTO userSession, HttpSession session, @PageableDefault(size = 12, sort = "id", direction = Sort.Direction.DESC)
    Pageable pageable,@RequestParam(value = "categoryId",defaultValue = "1") int categoryId) throws Exception {

                if(categoryId == 1) {


                    SearchRequirements searchRequirements = SearchRequirements.builder()
                            .blindStatus(false)
                            .build();

                    searchRequirements.setPageable(PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                            pageable.getPageSize(), Sort.Direction.DESC, "uploadDate"));


                    List<ProductListDTO> categoryList1 = productService.getProductCategoryList(1L, 8L);
                 //   List<ProductListDTO> categoryList1 = productService.getProductCategoryList(9L, 16L);

                    List<Long> CategoryIdList = new ArrayList<>();




                    for (int i = 0; i < categoryList1.size(); i++) {

                      //  CategoryIdList.add(0, categoryList1.get(i).getCategoryId());
                        System.out.println(categoryList1.get(i).getCategoryId());
                    }

                    searchRequirements.setCategoryIdList(CategoryIdList);

                    Page<ProductListDTO> page = productService.getProductListDTO(userSession.getId(), searchRequirements);


                    System.out.println(page.getSize());




                    model.addAttribute("page", page);
                    model.addAttribute("list", page.getContent());


                    //  model.addAttribute("list",categoryList1)
//               break;
//
//            case "9":
//                List<ProductListDTO> categoryList2 = productService.getProductCategoryList(9L, 11L);
//                model.addAttribute("list", categoryList2);
//                break;
//
//            case 12: List<ProductListDTO> categoryList3 = productService.getProductCategoryList(12L, 16L);
//                model.addAttribute("list",categoryList3);break;
//            case 17: List<ProductListDTO> categoryList4 = productService.getProductCategoryList(17L, 26L);
//                model.addAttribute("list",categoryList4);break;
//            case 27: List<ProductListDTO> categoryList5 = productService.getProductCategoryList(27L, 41L);
//                model.addAttribute("list",categoryList5);break;
//            case 42: List<ProductListDTO> categoryList6 = productService.getProductCategoryList(42L, 56L);
//                model.addAttribute("list",categoryList6);break;
//            case 57: List<ProductListDTO> categoryList7 = productService.getProductCategoryList(57L, 60L);
//                model.addAttribute("list",categoryList7);break;
//            case 61: List<ProductListDTO> categoryList8 = productService.getProductCategoryList(61L, 70L);
//                model.addAttribute("list",categoryList8);break;
//            case 71: List<ProductListDTO> categoryList9 = productService.getProductCategoryList(71L, 85L);
//                model.addAttribute("list",categoryList9);break;
//            case 86: List<ProductListDTO> categoryList10 = productService.getProductCategoryList(86L, 89L);
//                model.addAttribute("list",categoryList10);break;
//            case 90: List<ProductListDTO> categoryList11 = productService.getProductCategoryList(90L, 95L);
//                model.addAttribute("list",categoryList11);break;
//            case 96: List<ProductListDTO> categoryList12 = productService.getProductCategoryList(96L, 104L);
//                model.addAttribute("list",categoryList12);break;
//            case 105: List<ProductListDTO> categoryList13 = productService.getProductCategoryList(105L, 113L);
//                model.addAttribute("list",categoryList13);break;
//            case 114: List<ProductListDTO> categoryList14 = productService.getProductCategoryList(114L, 115L);
//                model.addAttribute("list",categoryList14);break;
//


                }

   return "product/list";
}

    @GetMapping("/personalList/{memberId}")
    public String productList(@PathVariable Long memberId, Model model, @PageableDefault(size = 12, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ProductListDTO> page = productService.getPersonalList(memberId, pageable);
        model.addAttribute("list", page.getContent());
        return "product/list";
    }

    @GetMapping("/update/{productId}")//상품 업데이트 주소
    public String update(Model model, @PathVariable("productId") Long productId, @LoginUser SessionDTO userSession, HttpServletResponse response) throws IOException {

        /*세션 없을 경우 로그인 처리*/
        if (userSession == null) {
            AlertRedirect.warningMessage(response, "/member/login", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }
        /*세션아이디로 동네 조회*/
        List<TownFindDTO> townList = townService.townLists(userSession.getId());

        /*ProductDTO 및 ProductImageDTO 조회*/
        ProductFindDTO productFindDTO = productService.getProductFindDTO(productId);
        List<ProductImageDTO> imageList = productImageService.getProductImage(productId);

        /*카테고리 조회*/
        List<Category> topCategory = categoryService.findTopCategory();

        model.addAttribute("imageList", imageList);
        model.addAttribute("townList", townList);
        model.addAttribute("topCategory", topCategory);
        model.addAttribute("productFindDTO", productFindDTO);
        model.addAttribute("productId", productId);

        return "product/updateProduct";
    }

    // 상품상태 수정
    @GetMapping("progressUpdate/{productId}/{productProgress}/{pageNumber}")
    public String progressUpdate(@PathVariable Long productId, @PathVariable String productProgress, @PathVariable int pageNumber) {
        productService.progressUpdate(productId, productProgress);
        return "redirect:/order/sellList?productProgress=" + productProgress + "&page=" + pageNumber;
    }

    @PostMapping("/update/{productId}")//실제 상품 업데이트 주소
    public String updateProduct(Model model, Long productId, ProductUpdateDTO updateDTO, BindingResult bindingResult,
                                @LoginUser SessionDTO userSession, HttpServletResponse response) throws Exception {

        if (prohibitionKeywordService.ProhibitionKeywordFind(updateDTO.getSubject())) { //금지키워드가있으면 true
            bindingResult.addError(new FieldError("productAddDTO", "subject", "적합하지 않은 단어가 포함되어 있습니다."));

            if (bindingResult.hasErrors()) {
                return "product/updateProduct";
            }
        }
        /*세션 없을 경우 로그인 처리*/
        if (userSession == null) {
            AlertRedirect.warningMessage(response, "/member/login", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }

        /*상품 정보 업데이트*/
        Long updateId = productService.updateProduct(productId, updateDTO);

        model.addAttribute("productId", updateId);

        return "redirect:/product/detail/" + updateId;//상품 상세페이지로 이동
    }

    @GetMapping("/delete/{productId}")//상품 삭제 주소
    public String removeProduct(@PathVariable("productId") Long productId, @LoginUser SessionDTO userSession, HttpServletResponse response)
            throws Exception {

        if (userSession == null) {
            AlertRedirect.warningMessage(response, "/member/login", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }else {
            //DB삭제가 아닌 boolean사용
            productService.deleteProduct(productId);
            return AlertRedirect.warningMessage(response,"/product/wishRangeList", "삭제가 완료되었습니다.");//삭제 후 메인 화면
        }
    }

    @GetMapping("/category/{topCategoryId}") //하위 카테고리 호출
    @ResponseBody
    public List<CategoryFindDTO> findSubCategory(@PathVariable("topCategoryId") Long topCategoryId) {
        List<CategoryFindDTO> subCategory = categoryService.findSubCategory(topCategoryId);

        return subCategory;
    }
}