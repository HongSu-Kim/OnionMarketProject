package com.youprice.onion.service.product.impl;

import com.youprice.onion.dto.product.*;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.order.Order;
import com.youprice.onion.entity.product.*;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.member.ProhibitionKeywordRepositoy;
import com.youprice.onion.repository.product.*;
import com.youprice.onion.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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

        // 상품 등록
        Product product = new Product(member,town,category,order,productAddDTO.getSubject(),productAddDTO.getContent(),productAddDTO.getPrice());

        if(productAddDTO.getAuctionStatus()!=true) {
            productAddDTO.setAuctionDeadline(null);
        }

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
    public Long updateProduct(Long productId,ProductUpdateDTO updateDTO) throws Exception {

        Product product = productRepository.findById(productId).orElse(null);
        product.updateProduct(productId,updateDTO);

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

    //상품 삭제
    @Override
    @Transactional
    public void deleteProduct(Long productId) throws Exception {

        productImageRepository.deleteById(productId);
        productRepository.deleteById(productId);
    }

    //전체 상품 조회
    @Override
    public List<ProductListDTO> getProductList() {
        return productRepository.findAll().stream()
                .map(product -> new ProductListDTO(product))
                .collect(Collectors.toList());
    }

    //상품 하나에 대한 데이터
    @Override
    public ProductDTO getProductDTO(Long productId) {
        return productRepository.findById(productId).map(ProductDTO::new).orElse(null);
    }

    //이미지리스트
    public List<ProductImage> productImages(Long productId, List<MultipartFile> fileList) throws Exception{
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

    //이미지파일 경로,저장
    public String filePath(MultipartFile multipartFile)throws  Exception{
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\product";

        if(multipartFile.isEmpty()){
            return null;
        }

        String fileName = multipartFile.getOriginalFilename();

        multipartFile.transferTo(new File(filePath, fileName));

        return fileName;
    }

    //조회수 증가
    @Override
    @Transactional
    public int updateView(Long productId) {
        return productRepository.updateView(productId);
    }

    //동네 번호 조회
    public TownFindDTO findTownId(String townName) {
        return townRepositoy.findByCoordinateTownName(townName).map(TownFindDTO::new).orElse(null);
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
