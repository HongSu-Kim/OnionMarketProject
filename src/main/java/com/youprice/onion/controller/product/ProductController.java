package com.youprice.onion.controller.product;

import com.youprice.onion.dto.chat.ChatDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.dto.product.*;
import com.youprice.onion.entity.product.ProductProgress;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.chat.ChatService;
import com.youprice.onion.service.member.BlockService;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.member.ProhibitionKeywordService;
import com.youprice.onion.service.product.*;
import com.youprice.onion.util.AlertRedirect;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    private final CoordinateService coordinateService;
    private final BiddingService biddingService;
    private final MemberService memberService;
    private final BlockService blockService;
    private final ProhibitionKeywordService prohibitionKeywordService;
    private final ChatService chatService;
    private final SimpMessagingTemplate template;

    @GetMapping("add")//?????? ?????? ??????
    public String add(Model model, @LoginUser SessionDTO userSession, HttpServletResponse response) throws IOException {

        //Session??? ?????? ?????? ????????? ??????
        if (userSession == null) {
            AlertRedirect.warningMessage(response, "/member/login", "???????????? ???????????????.");
            return "redirect:/member/login";
        }

        /*?????????????????? ?????? ??????*/
        List<TownFindDTO> townList = townService.townLists(userSession.getId());
        //??????????????? ?????? ?????? ?????? ??????
        if (townList.size() == 0) {
            AlertRedirect.warningMessage(response, "/town/town", "??? ????????? ?????? ??????????????????.");
            return null;
        }

        /*???????????? ??????*/
        List<CategoryFindDTO> topCategoryList = categoryService.findTopCategory();
        model.addAttribute("townList", townList);
        model.addAttribute("topCategoryList", topCategoryList);


        return "product/addProduct";//???????????? ?????????
    }

    @PostMapping("add")//?????? ?????? ?????? ??????
    public String addProduct(@LoginUser SessionDTO userSession, ProductAddDTO productAddDTO, BindingResult bindingResult,
                             HttpServletResponse response, List<MultipartFile> fileList, Model model) throws Exception {

        if (prohibitionKeywordService.ProhibitionKeywordFind(productAddDTO.getSubject())) { //??????????????????????????? true
            bindingResult.addError(new FieldError("productAddDTO", "subject", "???????????? ?????? ????????? ???????????? ????????????."));

            if (bindingResult.hasErrors()) {
                return "product/addProduct";
            }
        }
        if (productAddDTO.getCategoryId() == null) {
            AlertRedirect.warningMessage(response, "/product/add", "??????????????? ??????????????????.");

            return "redirect:/product/addProduct";
        }
        /*?????????????????? ??????????????? set*/
        productAddDTO.setMemberId(userSession.getId());
        /*?????? ??????*/
        Long productId = productService.addProduct(productAddDTO, fileList);

        // ????????? ??????
        List<ChatDTO> chatDTOList = chatService.alertChat(productId, productAddDTO.getSubject());
        for (ChatDTO chatDTO : chatDTOList) {
            template.convertAndSend("/sub/chat/" + chatDTO.getTargetId(), chatDTO);
        }

        model.addAttribute("productId", productId);

        return "redirect:/product/detail/" + productId;//?????? ?????????????????? ??????
    }

    @GetMapping("/detail/{productId}")//?????? ??????????????? ??????
    public String detail(@PathVariable("productId") Long productId, @LoginUser SessionDTO userSession, Model model,
                         HttpServletResponse response) throws Exception {

        /*????????? ??????*/
        productService.updateView(productId);
        /*???????????? ??? ?????? ??????*/
        ProductFindDTO productFindDTO = productService.getProductFindDTO(productId);
        if (productFindDTO.getProductProgress() == ProductProgress.BLIND) {
            AlertRedirect.warningMessage(response, "/product/wishRangeList", "????????? ???????????????.");

            return "redirect:/product/wishRangeList";
        }
        /*?????? ??????*/
        Double reviewAvg = memberService.getMemberDTO(productFindDTO.getMemberId()).getUserGrade();

        /*?????? ????????? ?????? ??? ????????? ????????? ??????*/
        List<BiddingListDTO> biddingList = biddingService.getBiddingList(productId, model);
        if (biddingList.size() > 0) {
            int bid = biddingList.get(0).getBid();

            model.addAttribute("bid", bid);
        }

        /*?????? ???????????? ???????????? ??????*/
        List<ProductFindDTO> categoryDTO = productService.getProductSubCategory(productFindDTO.getProductId(), productFindDTO.getCategoryId());

        model.addAttribute("userSession", userSession);
        model.addAttribute("productFindDTO", productFindDTO);
        model.addAttribute("reviewAvg", reviewAvg);
        model.addAttribute("biddingList", biddingList);
        model.addAttribute("categoryDTO", categoryDTO);

        return "product/detail";
    }

    @PostMapping("bid")//?????? ?????? ??????
    public String bidProduct(@LoginUser SessionDTO userSession, BiddingAddDTO biddingAddDTO,
                             HttpServletResponse response, Model model) throws Exception {

        //Session??? ?????? ?????? ????????? ??????
        if (userSession == null) {
            AlertRedirect.warningMessage(response, "/member/login", "???????????? ???????????????.");
            return "redirect:/member/login";
        }

        biddingAddDTO.setMemberId(userSession.getId());
        Long productId = biddingAddDTO.getProductId();
        /*?????? ?????? ??????*/
        biddingService.bidProduct(biddingAddDTO);

        model.addAttribute("productId", productId);
        model.addAttribute("bid", biddingAddDTO.getBid());

        return "redirect:/product/detail/" + productId;
    }

    //?????? ??????????????? ??????
    @GetMapping(value = "wishRangeList")
    public String list(@LoginUser SessionDTO sessionDTO, HttpSession session, Model model,
                       @PageableDefault(size = 12, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {

        /*?????????????????? ?????? ??????*/
        Long memberId = null;
        List<Long> blockIdList = null;
        List<Long> coordinateList = null;

        if (sessionDTO != null) {

            blockIdList = blockService.blockIdList(sessionDTO.getId());
            coordinateList = townService.townLists(sessionDTO.getId())
                    .stream()
                    .map(TownFindDTO::getCoordinateId)
                    .collect(Collectors.toList());

            memberId = sessionDTO.getId();
        }
        SearchRequirements searchRequirements = SearchRequirements.builder()
                .blindStatus(false)
                .blockIdList(blockIdList)
                .coordinateIdList(coordinateList)
                .build();

        searchRequirements.setPageable(pageable);
        searchRequirements.setCoordinateIdList((List<Long>) session.getAttribute("rangeList"));

        Page<ProductListDTO> page = productService.getProductListDTO(memberId, searchRequirements);

        model.addAttribute("page", page);


        return "product/wishRangeList";
    }

    @PostMapping("wishRangeList")
    public String allList(@LoginUser SessionDTO sessionDTO, Model model, @PageableDefault(size = 12, sort = "uploadDate", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam("range") Double range
            , @RequestParam("townName") String townName, HttpSession session) {

        List<Long> blockIdList = null;
        if(sessionDTO != null) {
            blockIdList = blockService.blockIdList(sessionDTO.getId());
        }

        List<Long> rangeList = coordinateService.coordinateSearch(townName, range);
        session.setAttribute("rangeList", rangeList);

        SearchRequirements searchRequirements = SearchRequirements.builder()
                .pageable(pageable)
                .blindStatus(false)
                .blockIdList(blockIdList)
                .coordinateIdList(rangeList)
                .build();

        Long memberId = sessionDTO == null ? null : sessionDTO.getId();

        Page<ProductListDTO> page = productService.getProductListDTO(memberId, searchRequirements);

        model.addAttribute("page", page);


        return "product/wishRangeList";

    }

    @GetMapping("auctionList") //?????? ?????? ?????????
    public String auctionList(@LoginUser SessionDTO sessionDTO, HttpSession session, Model model,
                              @PageableDefault(size = 12, sort = "auctionDeadline", direction = Sort.Direction.ASC) Pageable pageable) throws Exception {

        Long memberId = null;
        List<Long> blockIdList = null;

        if(sessionDTO != null) {
            blockIdList = blockService.blockIdList(sessionDTO.getId());
            memberId = sessionDTO.getId();
        }

        SearchRequirements searchRequirements = SearchRequirements.builder()
                .blindStatus(false)
                .auctionStatus(true)
                .blockIdList(blockIdList)
                .build();

        searchRequirements.setPageable(pageable);

        Page<ProductListDTO> page = productService.getProductListDTO(memberId, searchRequirements);

        model.addAttribute("page", page);

        return "product/auctionList";//?????? ????????? ?????? ???????????????
    }

    @GetMapping(value = "category")//?????? ??????????????? ?????? ??????
    public String category(@LoginUser SessionDTO sessionDTO, Model model, Long categoryId,
                           @PageableDefault(size = 12, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        List<Long> blockIdList = null;
        if(sessionDTO != null) {
            blockIdList = blockService.blockIdList(sessionDTO.getId());
        }

        List<Long> categoryIdList = categoryService.findSubCategory(categoryId)
                .stream().map(CategoryFindDTO::getCategoryId)
                .collect(Collectors.toList());

        SearchRequirements searchRequirements = SearchRequirements.builder()
                .pageable(pageable)
                .blindStatus(false)
                .blockIdList(blockIdList)
                .categoryIdList(categoryIdList)
                .build();

        Long memberId = sessionDTO == null ? null : sessionDTO.getId();

        Page<ProductListDTO> page = productService.getProductListDTO(memberId, searchRequirements);

        model.addAttribute("categoryId", categoryId);
        model.addAttribute("page", page);

        return "product/list";
    }

    @GetMapping("/personalList/{memberId}")
    public String productList(@PathVariable Long memberId, Model model, @PageableDefault(size = 12, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ProductListDTO> page = productService.getPersonalList(memberId, pageable);
        model.addAttribute("page", page);
        return "product/list";
    }

    @GetMapping("/update/{productId}")//?????? ???????????? ??????
    public String update(Model model, @PathVariable("productId") Long productId, @LoginUser SessionDTO userSession, HttpServletResponse response) throws IOException {

        /*?????? ?????? ?????? ????????? ??????*/
        if (userSession == null) {
            AlertRedirect.warningMessage(response, "/member/login", "???????????? ???????????????.");
            return "redirect:/member/login";
        }
        /*?????????????????? ?????? ??????*/
        List<TownFindDTO> townList = townService.townLists(userSession.getId());

        /*ProductDTO ??? ProductImageDTO ??????*/
        ProductFindDTO productFindDTO = productService.getProductFindDTO(productId);

        /*???????????? ??????*/
        List<CategoryFindDTO> topCategoryList = categoryService.findTopCategory();
        Long categoryId = productFindDTO.getCategoryParentId();
        List<CategoryFindDTO> subCategoryList = categoryService.findSubCategory(categoryId);

        model.addAttribute("townList", townList);
        model.addAttribute("topCategoryList", topCategoryList);
        model.addAttribute("subCategoryList", subCategoryList);
        model.addAttribute("productFindDTO", productFindDTO);

        return "product/updateProduct";
    }

    @PostMapping("/update/{productId}")//?????? ?????? ???????????? ??????
    @PreAuthorize("isAuthenticated()")
    public String updateProduct(@PathVariable Long productId, ProductUpdateDTO updateDTO, BindingResult bindingResult,
                                HttpServletResponse response) throws Exception {

        if (prohibitionKeywordService.ProhibitionKeywordFind(updateDTO.getSubject())) { //??????????????????????????? true
            bindingResult.addError(new FieldError("productAddDTO", "subject", "???????????? ?????? ????????? ???????????? ????????????."));
        }

        if (bindingResult.hasErrors()) {
            return "product/updateProduct";
        }

        /*?????? ?????? ????????????*/
        try {
            productService.updateProduct(productId, updateDTO);
        } catch (ArrayIndexOutOfBoundsException e) {
            // ????????? ????????? ?????? ??????
            AlertRedirect.warningMessage(response, e.getMessage());
        }

        return "redirect:/product/detail/" + productId;//?????? ?????????????????? ??????
    }

    // ???????????? ??????
    @GetMapping("progressUpdate/{productId}/{productProgress}")
    @PreAuthorize("isAuthenticated()")
    public String progressUpdate(@PathVariable Long productId, @PathVariable String productProgress) {
        productService.progressUpdate(productId, productProgress);
        return "redirect:/order/sellList?productProgress=" + productProgress;
    }

    @GetMapping("/delete/{productId}")//?????? ?????? ??????
    public String removeProduct(@PathVariable("productId") Long productId, @LoginUser SessionDTO userSession, HttpServletResponse response)
            throws Exception {

        ProductFindDTO productFindDTO = productService.getProductFindDTO(productId);

        if (userSession == null) {
            AlertRedirect.warningMessage(response, "/member/login", "???????????? ???????????????.");
            return "redirect:/member/login";
        } else if (productFindDTO.getProductProgress() != ProductProgress.SALESON) {
            return AlertRedirect.warningMessage(response, "/order/sellList", "?????? ?????? ????????? ????????? ???????????????.");//?????? ??? ?????? ??????
        } else {
            //DB????????? ?????? boolean??????
            productService.deleteProduct(productId);
            return AlertRedirect.warningMessage(response, "/order/sellList", "????????? ?????????????????????.");//?????? ??? ?????? ??????
        }
    }

    @GetMapping("/category/{topCategoryId}") //?????? ???????????? ??????
    @ResponseBody
    public List<CategoryFindDTO> findSubCategory(@PathVariable("topCategoryId") Long topCategoryId) {
        List<CategoryFindDTO> subCategory = categoryService.findSubCategory(topCategoryId);

        return subCategory;
    }
}