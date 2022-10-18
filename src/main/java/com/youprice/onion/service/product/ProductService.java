package com.youprice.onion.service.product;

import com.youprice.onion.dto.order.ProductSellListDTO;
import com.youprice.onion.dto.product.*;
import com.youprice.onion.entity.product.ProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    //상품등록
    Long addProduct(ProductAddDTO productAddDTO, List<MultipartFile> fileList) throws  Exception;
    //상품수정
    Long updateProduct(Long productId, ProductUpdateDTO updateDTO, List<MultipartFile> fileList) throws Exception;
    //상품 삭제
    void deleteProduct(Long productId) throws Exception;

    //상품목록 전체
    List<ProductListDTO> getProductList();
    //상품 하나 조회
    ProductDTO getProductDTO(Long productId);

    //조회수 증가
    int updateView(Long productId);

    //동네번호 조회
    TownFindDTO findTownId(String townName);

    //카테고리번호 조회
    CategoryFindDTO findCategoryId(Long categoryId);

	Page<ProductSellListDTO> getProductSellListDTO(Long memberId, Pageable pageable);

}
