package com.youprice.onion.entity.member;

import com.youprice.onion.entity.board.Complain;
import com.youprice.onion.entity.board.Inquiry;
import com.youprice.onion.entity.board.Notice;
import com.youprice.onion.entity.chat.Chatroom;
import com.youprice.onion.entity.order.Order;
import com.youprice.onion.entity.order.Wish;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.Town;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id")
    private Long id; //회원번호 PK

    @Enumerated(EnumType.STRING)
    private Role role; //권한(user or admin)

    @Column(name = "user_id")
    private String userId; //아이디

    private String pwd; //비밀번호
    private String name; //이름
    private String nickname; //닉네임
    private LocalDate birth; //생일
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
    private List<Chatroom> chatroomList = new ArrayList<>(); //채팅방-회원번호 FK

    @OneToMany(mappedBy = "member")
    private List<Notice> noticeList = new ArrayList<>(); //게시판-회원번호 FK

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

//    @OneToMany(mappedBy = "member")
//    private List<Review> reviewList = new ArrayList<>(); //리뷰-회원번호 FK

    @Builder
    public Member(Long id, Role role, String userId, String pwd, String name, String nickname, LocalDate birth, String tel, String postcode, String address, String detailAddress, String extraAddress, String email, String memberImageName, int cash, int point, int userGrade, int complaintCount) {

        this.id = id;
        this.role = role;
        this.userId = userId;
        this.pwd = pwd;
        this.name = name;
        this.nickname = nickname;
        this.birth = birth;
        this.tel = tel;
        this.address = new Address(postcode, address, detailAddress, extraAddress);
        this.email = email;
        this.memberImageName = memberImageName;
        this.cash = cash;
        this.point = point;
        this.userGrade = userGrade;
        this.complaintCount = complaintCount;
    }

    //회원정보 수정
    public void modify(String pwd, String nickname, String tel, String postcode, String address, String detailAddress, String extraAddress, String email, String memberImageName) {
        this.pwd = pwd;
        this.nickname = nickname;
        this.tel = tel;
        this.address = new Address(postcode, address, detailAddress, extraAddress);
        this.email = email;
        this.memberImageName = memberImageName;
    }
}
