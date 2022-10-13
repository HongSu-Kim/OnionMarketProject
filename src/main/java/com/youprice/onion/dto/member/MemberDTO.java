package com.youprice.onion.dto.member;

import com.youprice.onion.entity.member.Address;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.member.Role;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

//request, response DTO 클래스를 하나로 묶어 InnerStaticClass로 한 번에 관리
public class MemberDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request { //회원 service 요청(Request) DTO 클래스
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

    @Getter
    public static class Response implements Serializable { //인증된 사용자 정보를 세션에 저장하기 위한 클래스
        //엔티티 클래스에 직렬화를 해준다면 추후에 다른 엔티티와 연관관계를 맺을 시,
        //직렬화 대상에 다른 엔티티까지 포함될 수 있어 성능 이슈, 부수 효과 우려가 있기 때문에 세션 저장용 DTO 클래스 생성

        private final Long id;
        private final String userId;
        private final String name;
        private final String nickname;
        private final String tel;
        private final String email;
        private final String memberImageName;

        //Entity -> DTO
        public Response(Member member) {
            this.id = member.getId();
            this.userId = member.getUserId();
            this.name = member.getName();
            this.nickname = member.getNickname();
            this.tel = member.getTel();
            this.email = member.getEmail();
            this.memberImageName = member.getMemberImageName();
        }
    }

}
