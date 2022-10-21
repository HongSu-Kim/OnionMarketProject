package com.youprice.onion.dto.member;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberFindDTO { //회원가입 할 때 필요한 DTO (MemberDTO)에 유효성 검증 어노테이션 설정할 경우,
                            //회원가입이 아닌 다른 곳에서 불러다 쓸 때 문제 생길 수 있어서 따로 클래스 생성


    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

}
