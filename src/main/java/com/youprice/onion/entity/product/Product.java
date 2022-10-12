package com.youprice.onion.entity.product;


import com.youprice.onion.dto.product.ProductDTO;
import com.youprice.onion.entity.chat.Chatroom;
import com.youprice.onion.entity.order.Order;
import com.youprice.onion.entity.order.Wish;
import com.youprice.onion.entity.board.Complain;
import com.youprice.onion.entity.member.Member;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;


@Entity
@Getter
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

    private String productName; //상품명
    private String subject; //제목
    private String content; //내용
    private int price; //상품가격
    private LocalDateTime uploadDate; //등록일
    private LocalDateTime updateDate; //수정일
    private int viewCount; //조회수

    @Enumerated(EnumType.STRING)
    private ProductProgress productProgress; //판매상태 Reserved,tradings,soldout 예약중,거래중,판매완료

    private String payStatus; //페이현황
    private String blindStatus; //블라인드현황

    @OneToMany(mappedBy = "product")//카테고리번호 FK
    private List<ProductCategory> productCategoryList = new ArrayList<>();

    @OneToMany(mappedBy = "product")//이미지번호
    private List<ProductImage> productImageList = new ArrayList<>();

    @OneToMany(mappedBy = "product")//태그번호
    private List<ProductTag> productTagList = new ArrayList<>();

    //주문내역 참조 양방향
    @OneToOne(mappedBy = "product")
    private Order order;

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

    //경매 참조 양방향
    @OneToOne(mappedBy = "product")
    private Auction auction;

    public Product createProduct(ProductDTO productDTO) {

        this.id = productDTO.getProductId();
        this.productName = productDTO.getProductName();
        this.subject = productDTO.getSubject();
        this.content = productDTO.getContent();
        this.price = productDTO.getPrice();
        this.uploadDate = LocalDateTime.now();

        return this;
    }
}













