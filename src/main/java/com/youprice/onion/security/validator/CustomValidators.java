package com.youprice.onion.security.validator;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CustomValidators {

    @RequiredArgsConstructor
    @Component
    public static class UserIdValidator extends AbstractValidator<MemberDTO> {
        private final MemberRepository memberRepository;

        @Override
        protected void doValidate(MemberDTO memberDTO, Errors errors) {
            if (memberRepository.existsByUserId(memberDTO.toEntity().getUserId())) {
                errors.rejectValue("userId", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
            }
        }
    }

    @RequiredArgsConstructor
    @Component
    public static class NicknameValidator extends AbstractValidator<MemberDTO> {
        private final MemberRepository memberRepository;

        @Override
        protected void doValidate(MemberDTO memberDTO, Errors errors) {
            if (memberRepository.existsByNickname(memberDTO.toEntity().getNickname())) {
                errors.rejectValue("nickname", "닉네임 중복 오류", "이미 사용중인 닉네임 입니다.");
            }
        }
    }

    @RequiredArgsConstructor
    @Component
    public static class EmailValidator extends AbstractValidator<MemberDTO> {
        private final MemberRepository memberRepository;

        @Override
        protected void doValidate(MemberDTO memberDTO, Errors errors) {
            if (memberRepository.existsByEmail(memberDTO.toEntity().getEmail())) {
                errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
            }
        }
    }
}
