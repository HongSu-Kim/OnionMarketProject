package com.youprice.onion.service.product;

import com.youprice.onion.dto.order.ProductSellListDTO;
import com.youprice.onion.dto.product.*;
import com.youprice.onion.entity.product.ProductProgress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    //상품 목록 조회
    Page<ProductListDTO> getProductListDTO(SearchRequirements searchRequirements);

    //상품등록
    Long addProduct(ProductAddDTO productAddDTO, List<MultipartFile> fileList) throws Exception;

    //상품수정
    Long updateProduct(Long productId, ProductUpdateDTO updateDTO) throws Exception;

    //상품상태 수정
    void progressUpdate(Long productId, String productProgress);

    //상품 삭제
    void deleteProduct(Long productId) throws Exception;

    //조회수 증가
    int updateView(Long productId);

    //경매상품 전체 조회
    List<ProductListDTO> getAuctionList(Boolean blindStatus);

    //경매 종료된 상품 처리 후 조회
    List<ProductListDTO> getProductAuctionList();

    //상품 하나 조회
    ProductDTO getProductDTO(Long productId);

    //상품 하나 조회
    ProductFindDTO getProductFindDTO(Long productId);

    //제목과 내용으로 검색
    List<ProductListDTO> getSearchList(String subject, String content);

    //카테고리별 상품 조회
    List<ProductListDTO> getProductCategoryList(Long start, Long end);

  //  List<ProductListDTO> getProductCategoryList(Long start);

    //하위 카테고리 조회
    List<ProductFindDTO> getProductSubCategory(Long productId, Long categoryId);

    //동네번호 조회
    TownFindDTO findTownId(String townName);

    Page<ProductSellListDTO> getProductSellListDTO(Long memberId, ProductProgress productProgress, Pageable pageable);

    Page<ProductListDTO> getPersonalList(Long memberId, Pageable pageable);
}
