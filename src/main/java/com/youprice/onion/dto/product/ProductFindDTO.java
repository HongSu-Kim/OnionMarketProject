package com.youprice.onion.dto.product;

import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductProgress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ProductFindDTO {

    private Long productId;
    private Long memberId; //Member FK
    private String nickname; //member nickname
    private Long townId; //Town FK
    private String townName; //동네 이름
    private Long categoryId; //Category FK
    private Long categoryParentId; //부모 Category FK
    private String categoryParentName; //부모 Category이름
    private String categoryName; //Category이름
    private String subject; //제목
    private String content; //내용
    private int price; //상품가격
    private String representativeImage; //대표이미지
    private LocalDateTime uploadDate; //등록시간
    private LocalDateTime updateDate; //수정일
    private LocalDateTime auctionDeadline; //경매기한
    private Boolean auctionStatus; //경매현황
    private int viewCount; //조회수
    private ProductProgress productProgress; //판매상태 SALESON,RESERVED,TRADINGS,SOLDOUT 판매중,예약중,거래중,판매완료
    private Boolean payStatus; //페이현황
    private Boolean blindStatus; //블라인드현황
    private List<ProductImageDTO> productImageDTOList; //이미지 리스트

    public ProductFindDTO(Product product) {

        productId = product.getId();
        memberId = product.getMember().getId();
        nickname = product.getMember().getNickname();
        townId = product.getTown().getId();
        townName = product.getTown().getCoordinate().getTownName();
        categoryId = product.getCategory().getId();
        categoryParentId = product.getCategory().getParent().getId();
        categoryParentName = product.getCategory().getParent().getCategoryName();
        categoryName = product.getCategory().getCategoryName();
        subject = product.getSubject();
        content = product.getContent();
        price = product.getPrice();
        representativeImage = product.getRepresentativeImage();
        uploadDate = product.getUploadDate();
        updateDate = product.getUpdateDate();
        auctionDeadline = product.getAuctionDeadline();
        if(product.getAuctionDeadline()!=null){
            auctionStatus = true;
        }else auctionStatus = false;
        viewCount = product.getViewCount();
        productProgress = product.getProductProgress();
        payStatus = product.getPayStatus();
        blindStatus = product.getBlindStatus();
        productImageDTOList = product.getProductImageList()
                .stream().map(ProductImageDTO::new)
                .collect(Collectors.toList());

    }
}
