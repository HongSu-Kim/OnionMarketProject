package com.youprice.onion.service.product;

import com.youprice.onion.dto.order.ProductSellListDTO;
import com.youprice.onion.dto.product.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    //상품 목록 조회
    Page<ProductListDTO> getProductListDTO(Long memberId, SearchRequirements searchRequirements);

    //상품등록
    Long addProduct(ProductAddDTO productAddDTO, List<MultipartFile> fileList) throws Exception;

    //상품수정
    void updateProduct(Long productId, ProductUpdateDTO updateDTO) throws Exception;

    //상품상태 수정
    void progressUpdate(Long productId, String productProgress);

    //상품 삭제
    void deleteProduct(Long productId) throws Exception;

    //조회수 증가
    int updateView(Long productId);

    //상품 하나 조회
    ProductDTO getProductDTO(Long productId);

    //상품 하나 조회
    ProductFindDTO getProductFindDTO(Long productId);

    //하위 카테고리 조회
    List<ProductFindDTO> getProductSubCategory(Long productId, Long categoryId);

    Page<ProductSellListDTO> getProductSellListDTO(SearchRequirements searchRequirements);

    Page<ProductListDTO> getPersonalList(Long memberId, Pageable pageable);
}
