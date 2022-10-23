package com.youprice.onion.dto.product;

import com.youprice.onion.entity.product.ProductProgress;
import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 검색 조건
public class SearchRequirements {

	private Pageable pageable;//페이징
	private Long memberId;//회원번호
	private Long coordinateId;//지역번호
	private Long categoryId;//카테고리번호
	private ProductProgress productProgress;//판매상태
	private Boolean blindStatus;//블라인드현황
	private String searchValue;//검색어
}
