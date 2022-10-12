package com.youprice.onion.dto.member;

import com.youprice.onion.entity.member.Address;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.member.Role;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {

    private Long id;
    private Role role;
    private String userId;
    private String pwd;
    private String name;
    private String nickname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    private String tel;
    private String postcode;
    private String address;
    private String detailAddress;
    private String extraAddress;
    private String email;
    private String memberImageName;
    private int cash;
    private int point;
    private int userGrade;
    private int complaintCount;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .role(role.USER)
                .userId(userId)
                .pwd(pwd)
                .name(name)
                .nickname(nickname)
                .birth(birth)
                .tel(tel)
                .postcode(postcode)
                .address(address)
                .detailAddress(detailAddress)
                .extraAddress(extraAddress)
                .email(email)
                .memberImageName(memberImageName)
                .cash(cash)
                .point(point)
                .userGrade(userGrade)
                .complaintCount(complaintCount)
                .build();
    }

/*
    @Builder
    public MemberDTO(Long id, Role role, String userId, String pwd, String name, String nickname, LocalDate birth, String tel, String postcode, String address, String detailAddress, String extraAddress, String email, String memberImageName, int cash, int point, int userGrade, int complaintCount) {

        this.id = id;
        this.role = role;
        this.userId = userId;
        this.pwd = pwd;
        this.name = name;
        this.nickname = nickname;
        this.birth = birth;
        this.tel = tel;
        this.postcode = postcode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.extraAddress = extraAddress;
        this.email = email;
        this.memberImageName = memberImageName;
        this.cash = cash;
        this.point = point;
        this.userGrade = userGrade;
        this.complaintCount = complaintCount;
    }
*/

}
