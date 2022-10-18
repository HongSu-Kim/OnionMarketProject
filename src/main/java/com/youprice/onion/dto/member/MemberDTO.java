package com.youprice.onion.dto.member;

import com.youprice.onion.entity.member.Address;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.member.Role;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {

    private Long id;
    private Role role;
    private String userId;
    private String pwd;
    private String name;
    private String nickname;
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

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.role = member.getRole();
        this.userId = member.getUserId();
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.birth = member.getBirth();
        this.tel = member.getTel();
        this.postcode = member.getAddress().getPostcode();
        this.address = member.getAddress().getAddress();
        this.detailAddress = member.getAddress().getDetailAddress();
        this.extraAddress = member.getAddress().getExtraAddress();
        this.email = member.getEmail();
        this.memberImageName = member.getMemberImageName();
        this.cash = member.getCash();
        this.point = member.getPoint();
        this.userGrade = member.getUserGrade();
        this.complaintCount = member.getComplaintCount();
    }

    public Member toEntity() { //DTO -> Entity
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

}
