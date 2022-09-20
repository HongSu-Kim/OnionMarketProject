package com.youprice.onion.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id")
    private Integer id;//회원번호 PK

    private String role; //권한(user or admin)

    @Column(name = "user_id")
    private String userId; //아이디

    private String pwd; //비밀번호
    private String nickname; //닉네임
    private String name; //이름
    private String birth; //생일
    private String tel; //전화번호

    @Embedded
    private Address address; //주소

    private String email; //이메일
    private String memberImageName; //프로필 사진
    private int cash; //양파페이
    private int point; //포인트
    private int userGrade; //평점
    private int complaintCount; //신고접수 횟수

    @OneToMany(mappedBy = "member")
    private List<Follow> followList = new ArrayList<>(); //팔로우-회원번호 FK

    @OneToMany(mappedBy = "member")
    private List<Block> blockList = new ArrayList<>(); //차단-회원번호 FK

    @OneToMany(mappedBy = "member")
    private List<Inquiry> inquiryList = new ArrayList<>(); //1:1문의글-회원번호 FK

    @OneToMany(mappedBy = "member")
    private List<Complain> complainList = new ArrayList<>(); //신고-회원번호 FK

    @OneToMany(mappedBy = "member")
    private List<ChatRoom> chatRoomList = new ArrayList<>(); //채팅방-회원번호 FK

    @OneToMany(mappedBy = "member")
    private List<NoticeBoard> noticeBoardList = new ArrayList<>(); //게시판-회원번호 FK

    @OneToMany(mappedBy = "member")
    private List<Product> productList = new ArrayList<>(); //상품-회원번호 FK

    @OneToMany(mappedBy = "member")
    private List<MemberCategory> memberCategoryList = new ArrayList<>(); //회원카테고리설정-회원번호 FK

    @OneToMany(mappedBy = "member")
    private List<Keyword> keywordList = new ArrayList<>(); //키워드-회원번호 FK

    @OneToMany(mappedBy = "member")
    private List<Town> townList = new ArrayList<>(); //동네-회원번호 FK

    @OneToMany(mappedBy = "member")
    private List<Order> orderList = new ArrayList<>(); //주문내역-회원번호 FK

    @OneToMany(mappedBy = "member")
    private List<Wish> wishList = new ArrayList<>(); //찜-회원번호 FK

}
