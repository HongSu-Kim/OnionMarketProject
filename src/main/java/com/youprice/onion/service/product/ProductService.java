package com.youprice.onion.service.product;

import com.youprice.onion.dto.order.ProductSellListDTO;
import com.youprice.onion.dto.product.*;
import com.youprice.onion.entity.product.ProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ProductService {
    //상품등록
    Long addProduct(ProductAddDTO productAddDTO, List<MultipartFile> fileList) throws  Exception;
    //상품수정
    Long updateProduct(Long productId, ProductUpdateDTO updateDTO) throws Exception;
	//상품상태 수정
	void progressUpdate(Long productId, String productProgress);
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

    //유효성 검사 에러메시지 핸들링
    Map<String, String> validatorHandling(Errors errors);

    Page<ProductSellListDTO> getProductSellListDTO(Long memberId, Pageable pageable);

}
