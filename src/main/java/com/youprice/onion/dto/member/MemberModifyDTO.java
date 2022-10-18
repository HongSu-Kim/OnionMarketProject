package com.youprice.onion.dto.member;

import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.member.Role;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberModifyDTO { //회원가입 할 때 필요한 DTO (MemberDTO)에 유효성 검증 어노테이션 설정할 경우,
                            //회원가입이 아닌 다른 곳에서 불러다 쓸 때 문제 생길 수 있어서 따로 클래스 생성

    private Long id;
    private Role role;

    @NotBlank(message = "아이디는 필수 입력값입니다.")
    @Pattern(regexp = "^[a-z0-9]{5,20}$", message = "아이디는 5~20자리 영어 소문자와 숫자만 사용해 주세요.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
//    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,20}", message = "비밀번호는 8~20자 영문 소문자, 숫자, 특수문자를 사용하세요.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9]).{4,20}", message = "비밀번호는 4~20자리 영문 소문자, 숫자를 사용해 주세요.")
    private String pwd;

    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name;

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10 자리여야 합니다.")
    @Size(min = 2, max = 15, message = "2~15자 이내의 닉네임을 입력해 주세요.")
    private String nickname;

    @NotNull(message = "생년월일은 필수 입력값입니다.")
    @PastOrPresent(message = "생년월일은 과거 또는 현재의 날짜여야 합니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    @NotBlank(message = "휴대폰 번호는 필수 입력값입니다.")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "휴대폰 번호 형식이 올바르지 않습니다.")
    private String tel;

    @NotBlank(message = "우편번호는 필수 입력값입니다.")
    @Size(min = 5, max = 5, message = "우편번호 5자리를 입력해 주세요.")
    private String postcode;

    @NotBlank(message = "주소는 필수 입력값입니다.")
    private String address;

    @NotBlank(message = "상세주소는 필수 입력값입니다.")
    private String detailAddress;

    private String extraAddress;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    private String memberImageName;

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
                .build();
    }
}
