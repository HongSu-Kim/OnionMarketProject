package com.youprice.onion.dto.member;

import com.youprice.onion.entity.member.Address;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.member.Role;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

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
                .role(role)
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

/*
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class RequestMemberDTO{
        private long id;//회원번호
        private Role role; //권한

        @NotBlank(message = "아이디를 입력해 주세요.")
        @Pattern(regexp = "^[a-z0-9]{4,20}$", message = "아이디는 영어 소문자와 숫자만 사용하여 4~20자리여야 합니다.")
        private String userId; //아이디

        @NotBlank(message="비밀번호를 입력해 주세요.")
        @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[0-9])(?=\\S+$).{8,20}",
                message = "8~20자 이내의 영문 및 숫자를 입력해 주세요.")
        private String pwd;//비밀번호

        @NotBlank(message = "이름을 입력해 주세요.")
        private String name; //이름

        @NotBlank(message="닉네임을 입력해 주세요.")
        @Size(min = 3, max = 15, message = "3~15자 이내의 닉네임을 입력해 주세요.")
        private String nickname;//닉네임

        @NotBlank(message="생년월일을 입력해 주세요.")
        private LocalDate birth;//생년월일

        @NotBlank(message = "휴대폰 번호를 입력해주세요.")
        @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해 주세요.")
        private String tel; //전화번호

        @NotBlank(message = "주소를 입력해 주세요.")
        private Address address; //주소

        @NotBlank(message = "이메일 주소를 입력해 주세요.")
        @Email(message = "이메일 형식에 맞게 입력해 주세요.")
        private String email;//이메일 주소

        */
/* 암호화된 password *//*

        public void encryptPassword(String BCryptpassword) {
            this.pwd = BCryptpassword;
        }

        */
/* DTO -> Entity *//*

        public Member toEntity() {
            Member member = Member.builder()
                    .id(id)
                    .role(Role.USER)
                    .userId(userId)
                    .pwd(pwd)
                    .name(name)
                    .nickname(nickname)
                    .birth(birth)
                    .tel(tel)
                    .address(address)
                    .email(email)
                    .build();
            return member;
        }
    }

    */
/* 인증된 사용자 정보를 세션에 저장하기 위한 클래스
     *  세션에 저장하기 위헤 USER 엔티티 클래스를 직접 사용하면 직렬화가 필요함
     *  엔티티 클래스에 직렬화를 넣어주면 추후 다른 엔티티와 연관관계를 맺을 시
     *  직렬화 대상에 다른 엔티티까지 포함될 수 있으므로 성능 이슈 우려
     *  세션 저장용 응답 ResponseDto 클래스
     *//*

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseMemberDTO implements Serializable{
        private Long id;
        private Role role;
        private String userId;
        private String name;
        private String nickname;
        private LocalDate birth;
        private String tel;
        private Address address;
        private String email;

        */
/* Entity -> DTO *//*

        public ResponseMemberDTO(Member member) {
            this.id = member.getId();
            this.role = member.getRole();
            this.userId = member.getUserId();
            this.name = member.getName();
            this.nickname = member.getNickname();
            this.birth = member.getBirth();
            this.tel = member.getTel();
            this.address = member.getAddress();
            this.email = member.getEmail();
        }
    }
*/

}
