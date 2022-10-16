package com.youprice.onion.dto.product;

import com.youprice.onion.entity.product.Category;
import com.youprice.onion.entity.product.ProductProgress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductUpdateDTO {

    private Category category; //카테고리
    private String subject; //제목
    private String content; //내용
    private int price; //상품가격
    private LocalDateTime updateDate; //수정일
    private ProductProgress productProgress; //판매상태 Reserved,tradings,soldout 예약중,거래중,판매완료
    private String payStatus; //페이현황
    private String blindStatus; //블라인드현황
    //이미지는 배열로
    private List<MultipartFile> productImageName;//이미지 이름

}
