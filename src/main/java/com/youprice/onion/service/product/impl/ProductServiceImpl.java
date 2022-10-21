package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.order.ProductSellListDTO;
import com.youprice.onion.dto.product.*;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.order.Order;
import com.youprice.onion.entity.product.*;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.member.ProhibitionKeywordRepositoy;
import com.youprice.onion.repository.product.*;
import com.youprice.onion.service.product.ProductImageService;
import com.youprice.onion.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final MemberRepository memberRepository;
    private final TownRepositoy townRepositoy;
    private final CategoryRepositoy categoryRepository;
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    private final ProhibitionKeywordRepositoy prohibitionKeywordRepositoy;

    private final static String COOKIE = "alreadyViewCookie";

    //상품 등록
    @Override
    @Transactional
    public Long addProduct(ProductAddDTO productAddDTO, List<MultipartFile> fileList) throws Exception {

        Member member = memberRepository.findById(productAddDTO.getMemberId()).orElse(null);
        Town town = townRepositoy.findById(productAddDTO.getTownId()).orElse(null);
        Category category = categoryRepository.findById(productAddDTO.getCategoryId()).orElse(null);
        Order order = null;

        //대표이미지 설정
        productAddDTO.setRepresentativeImage(getImageName()+fileList.get(0).getOriginalFilename());
        //경매 현황=null -> 경매 기한=null
        if(productAddDTO.getAuctionStatus()!=true) {
            productAddDTO.setAuctionDeadline(null);
        }else{
            productAddDTO.setAuctionDeadline(LocalDateTime.now().plusDays(3));
        }

        // 상품 등록
        Product product = new Product(member,town,category,order,productAddDTO.getSubject(),productAddDTO.getContent(),
                productAddDTO.getPrice(),productAddDTO.getRepresentativeImage(),productAddDTO.getAuctionDeadline(),productAddDTO.getPayStatus());

        Long productId = productRepository.save(product).getId();

        // 상품 이미지 등록
        //반복으로 꺼내면서 하나씩 저장
        List<ProductImage> productImageList = productImages(productId,fileList);
        for(ProductImage productImage : productImageList){
            productImageRepository.save(productImage);
        }

        return productId;
    }

    //상품 수정
    @Override
    @Transactional
    public Long updateProduct(Long productId, ProductUpdateDTO updateDTO) throws Exception {

        //수정한 동네번호
        Town town = townRepositoy.findById(updateDTO.getTownId()).orElse(null);

        //수정한 카테고리번호
        Category category = categoryRepository.findById(updateDTO.getCategoryId()).orElse(null);

        //대표이미지 설정
        updateDTO.setRepresentativeImage(getImageName()+updateDTO.getProductImageName().get(0).getOriginalFilename());

        System.out.println("updateDTO = " + updateDTO.getRepresentativeImage());

        //경매 현황=null -> 경매 기한=null
        if(updateDTO.getAuctionStatus()!=true) {
            updateDTO.setAuctionDeadline(null);
        }else{
            updateDTO.setAuctionDeadline(LocalDateTime.now().plusDays(3));
        }

        Product product = productRepository.findById(productId).orElse(null);
        product.updateProduct(productId, town, category, updateDTO, updateDTO.getAuctionDeadline());


        // 상품 이미지 수정
        //반복으로 지우고 저장
        List<ProductImage> productImageList = productImageRepository.findByProductId(productId);
        for (ProductImage productImage : productImageList){
            productImageRepository.delete(productImage);
        }

        List<ProductImage> imageList = productImages(productId, updateDTO.getProductImageName());
        for(ProductImage productImage : imageList){
            productImageRepository.save(productImage);
        }
        productRepository.save(product);

        return productRepository.save(product).getId();


    }

	// 상품상태 수정
	@Override
	@Transactional
	public void progressUpdate(Long productId, String productProgress) {
		productRepository.findById(productId).map(product -> product.progressUpdate(productProgress)).orElse(null);
	}

	//상품 삭제(DB삭제가 아닌 조회불가상태로 변경)
    @Override
    @Transactional
    public void deleteProduct(Long productId) throws Exception {

        productImageRepository.deleteById(productId);
        productRepository.deleteById(productId);
    }

    //카테고리 전체 상품 조회
    @Override
    public List<ProductListDTO> getProductCategoryList(Long start, Long end) {
        return productRepository.findByCategoryIdBetween(start,end).stream().
                map(product -> new ProductListDTO(product))
                .collect(Collectors.toList());
    }

    //전체 상품 조회
    @Override
    public List<ProductListDTO> getProductList() {
        return productRepository.findAll().stream()
                .map(product -> new ProductListDTO(product))
                .collect(Collectors.toList());
    }

    //검색에 따른 조회(제목,카테고리,내용)
    @Override
    public List<ProductListDTO> getSearchList(String subject,String content) {
        return productRepository.findBySubjectContainingOrContentContaining(subject,content).stream()
                .map(product -> new ProductListDTO(product))
                .collect(Collectors.toList());
    }

    //상품 하나에 대한 데이터
    @Override
    public ProductDTO getProductDTO(Long productId) {
        return productRepository.findById(productId).map(ProductDTO::new).orElse(null);
    }

    //상품 하나에 대한 데이터
    @Override
    public ProductFindDTO getProductFindDTO(Long productId) {
        return productRepository.findById(productId).map(ProductFindDTO::new).orElse(null);
    }

    //이미지리스트
    private List<ProductImage> productImages(Long productId, List<MultipartFile> fileList) throws Exception{
        List<ProductImage> productImageList = new ArrayList<>();
        Product product = productRepository.findById(productId).orElse(null);


        for(MultipartFile file: fileList) {

            if(!file.isEmpty()) {
                String productImageName = filePath(file);
                ProductImage saveFile = new ProductImage(product, productImageName);
                productImageList.add(saveFile);
            }
        }
        return productImageList;
    }

    // imageName 생성
    @Transactional(readOnly = true)
    public String getImageName() {

        LocalDateTime now = LocalDateTime.now();
        String imageName;

        do {
            imageName = now.format(DateTimeFormatter.BASIC_ISO_DATE).substring(2)
                    + now.format(DateTimeFormatter.ISO_LOCAL_TIME).replaceAll(":","").substring(0,6)
//                    + //고유값
            ;
        } while (productImageRepository.findByProductImageName(imageName).isPresent());

        return imageName;
    }

    //이미지파일 경로,저장
    private String filePath(MultipartFile multipartFile)throws  Exception{
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\product";

        if(multipartFile.isEmpty()){
            return null;
        }

        String fileName = getImageName()+multipartFile.getOriginalFilename();

        multipartFile.transferTo(new File(filePath, fileName));

        return fileName;
    }

    //조회수 증가
    @Override
    @Transactional
    public int updateView(Long productId) {
        return productRepository.updateView(productId);
    }

    //동네번호 조회
    @Override
    public TownFindDTO findTownId(String townName) {
        return townRepositoy.findByCoordinateTownName(townName).map(TownFindDTO::new).orElse(null);
    }

    //카테고리번호 조회
    @Override
    public CategoryFindDTO findCategoryId(Long categoryId) {
        return categoryRepository.findById(categoryId).map(CategoryFindDTO::new).orElse(null);
    }

    @Override
    public Page<ProductSellListDTO> getProductSellListDTO(Long memberId, Pageable pageable) {
      return productRepository.findByMemberId(memberId, pageable).map(ProductSellListDTO::new);
    }
  

//    @Override
//    public String getFirstImage(List<ProductImageDTO> productImageList) throws Exception {
//
//        for(ProductImageDTO productImage : productImageList){
//
//            productImage = prdo;
//
//        }
//
//        return productImage;
//    }

    //    @Override
//    @Transactional
//    public int updateView(Long productId, HttpServletRequest req, HttpServletResponse resp) {
//
//        Cookie[] cookies = req.getCookies();
//        boolean checkCookie = false;
//        int result = 0;
//        if(cookies != null) {
//            for (Cookie cookie : cookies){
//                //조회 시 체크 true
//                if(cookie.getName().equals(COOKIE+productId)) checkCookie = true;
//            }
//            if(!checkCookie){
//                Cookie newCookie = addCookieForOverlap(productId);
//                resp.addCookie(newCookie);
//                result = productRepository.updateView(productId);
//            }
//        }else {
//            Cookie newCookie = addCookieForOverlap(productId);
//            resp.addCookie(newCookie);
//            result = productRepository.updateView(productId);
//        }
//        return result;
//    }
//
//    private Cookie addCookieForOverlap(Long productId) {
//        Cookie cookie = new Cookie(COOKIE+productId, String.valueOf(productId));
//        cookie.setComment("조회수 중복 방지");
//        cookie.setMaxAge(10000);
//        cookie.setHttpOnly(true);
//        return cookie;
//    }
}
