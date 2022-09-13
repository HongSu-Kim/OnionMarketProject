package com.youprice.onion.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id")
    private Integer id;//회원번호 PK

    @Column //(nullable = false, length = 30, unique = true)
    private String userId; //아이디

    @Column //(nullable = false)
    private String pwd; //비밀번호

    @Column //(nullable = false, unique = true)
    private String nickname; //닉네임

    @Column //(nullable = false)
    private String name; //이름

    @Column //(nullable = false)
    private String birth; //생일

    @Column //(nullable = false, length = 11)
    private String tel; //전화번호

    @Column //(nullable = false)
    private String email; //이메일 주소

    private int grade; //평점
    private int point; //포인트
    private int cash; //양파페이
    private int complaintCount; //신고접수 횟수
    private String profileImg; //프로필 사진

    @Column //(nullable = false)
    private String address; //주소

}
