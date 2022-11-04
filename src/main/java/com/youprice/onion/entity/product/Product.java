package com.youprice.onion.entity.product;


import com.youprice.onion.dto.product.ProductAddDTO;
import com.youprice.onion.dto.product.ProductListDTO;
import com.youprice.onion.dto.product.ProductUpdateDTO;
import com.youprice.onion.entity.chat.Chatroom;
import com.youprice.onion.entity.order.Order;
import com.youprice.onion.entity.order.Wish;
import com.youprice.onion.entity.board.Complain;
import com.youprice.onion.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "product_id")
    private Long id; //상품번호 PK

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "member_id")
    private Member member;//회원번호 FK

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "town_id")
    private Town town;//동네번호 FK

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "category_id")
    private Category category; //카테고리번호 FK

    private String subject; //제목
    private String content; //내용
    private int price; //상품가격
    private String representativeImage; //대표이미지
    private LocalDateTime uploadDate; //등록일
    private LocalDateTime updateDate; //수정일
    private LocalDateTime auctionDeadline; //경매기한

    @Column(columnDefinition = "integer default 0",nullable = false)
    private int viewCount; //조회수

    @Enumerated(EnumType.STRING)
    private ProductProgress productProgress; //판매상태 SALESON,RESERVED,TRADINGS,SOLDOUT 판매중,예약중,거래중,판매완료

    private Boolean payStatus; //페이현황
    private Boolean blindStatus; //블라인드현황

    //주문 참조 양방향
    @OneToMany(mappedBy = "product")
    private List<Order> orderList = new ArrayList<>();
    //이미지 참조 양방향
    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImageList = new ArrayList<>();

    //태그 참조 양방향
    @OneToMany(mappedBy = "product")
    private List<ProductTag> productTagList = new ArrayList<>();

    //찜 참조 양방향
    @OneToMany(mappedBy = "product")
    private  List<Wish> wishList = new ArrayList<>();

    //채팅방 참조 양방향
    @OneToMany(mappedBy = "product")
    private  List<Chatroom> chatroomList = new ArrayList<>();
    
    //신고 참조 양방향
    @OneToMany(mappedBy = "product")
    private  List<Complain> complainList = new ArrayList<>();

    //입찰 참조 양방향
    @OneToMany(mappedBy = "product")
    private List<Bidding> biddingList = new ArrayList<>();

    //상품 등록 시 정보 생성
    public Product(Member member,Town town,Category category,ProductAddDTO productAddDTO) {

        this.member = member;
        this.town = town;
        this.category = category;
        this.subject = productAddDTO.getSubject();
        this.content = productAddDTO.getContent();
        this.price = productAddDTO.getPrice();
        this.representativeImage = productAddDTO.getRepresentativeImage();
        this.uploadDate = LocalDateTime.now();
        //경매 현황=false -> 경매 기한=null
        if(productAddDTO.getAuctionStatus()!=true) {
            productAddDTO.setAuctionDeadline(null);
        }else{
            productAddDTO.setAuctionDeadline(LocalDateTime.now().plusHours(12));
        }
        this.auctionDeadline = productAddDTO.getAuctionDeadline();
        this.productProgress = ProductProgress.SALESON;
        this.payStatus = productAddDTO.getPayStatus();
        this.blindStatus = false;

    }

	public Product(Member member, Town town, Category category, String subject, String content, int price, String representativeImage, boolean payStatus) {
		this.member = member;
		this.town = town;
		this.category = category;
		this.subject = subject;
		this.content = content;
		this.price = price;
		this.representativeImage = representativeImage;
		this.uploadDate = LocalDateTime.now();
		this.auctionDeadline = null;
		this.productProgress = ProductProgress.SALESON;
		this.payStatus = payStatus;
		this.blindStatus = false;
	}

    //상품 수정 시 정보 변경
    public void updateProduct(Long productId, Town town, Category category, ProductUpdateDTO updateDTO) {

        this.id = productId;
        this.town = town;
        this.category = category;
        this.subject = updateDTO.getSubject();
        this.content = updateDTO.getContent();
        this.price = updateDTO.getPrice();
        this.representativeImage = updateDTO.getRepresentativeImage();
        this.updateDate = LocalDateTime.now();
        //경매 현황=false -> 경매 기한=null
        if(updateDTO.getAuctionStatus()!=true) {
           updateDTO.setAuctionDeadline(null);
        }else{
           updateDTO.setAuctionDeadline(LocalDateTime.now().plusHours(12));
        }
        this.auctionDeadline = updateDTO.getAuctionDeadline();
        this.payStatus = updateDTO.getPayStatus();
    }

    //경매 상품 가격&블라인드 수정
    public void updateAuctionProduct(ProductListDTO productListDTO){
        this.price = productListDTO.getPrice();
        this.blindStatus = productListDTO.getBlindStatus();
    }

	// 상품상태 수정
	public Product progressUpdate(ProductProgress productProgress) {
		this.productProgress = productProgress;
		return this;
	}

    // 상품 신고 처리
    public Product blindImage(String productImageName) {
        if(productImageName == null) {
            this.representativeImage = "blindImage.png";
        } else {
            this.representativeImage = productImageName;
        }
        return this;
    }
    
    // 블라인드 처리(삭제)
    public Product blindProduct(Boolean blindStatus) {
        this.blindStatus = blindStatus;
        return this;
    }
}





