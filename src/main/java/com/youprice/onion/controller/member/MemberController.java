package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.MemberJoinDTO;
import com.youprice.onion.security.validator.CustomValidators;
import com.youprice.onion.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {

    private final MemberService memberService;

    private final CustomValidators.UserIdValidator userIdValidator;
    private final CustomValidators.NicknameValidator nicknameValidator;
    private final CustomValidators.EmailValidator emailValidator;

    //회원가입 시 유효성 검증에 필요
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(userIdValidator);
        binder.addValidators(nicknameValidator);
        binder.addValidators(emailValidator);
    }

    //메인 페이지
    @GetMapping("/")
    public String homeView1() {
        return "member/home";
    }

    @GetMapping("/home")
    public String homeView2() {
        return "member/home";
    }

    //회원가입 페이지
    @GetMapping("/join")
    public String joinView() {
        return "member/join";
    }

    //회원가입
    @PostMapping("/joinProc")
    public String joinProc(@Valid MemberJoinDTO memberJoinDTO, Errors errors, Model model) {

        if (errors.hasErrors()) {
            //회원가입 실패 시 입력 데이터 값을 유지
            model.addAttribute("memberJoinDTO", memberJoinDTO);

            //유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            //회원가입 페이지로 다시 리턴
            return "member/join";
        }
        memberService.saveMember(memberJoinDTO);
        return "redirect:login";
    }

    //로그인 페이지
    @GetMapping("/login")
    public String loginView() {
        return "member/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "member/logout";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/info")
    public String infoView() {
        return "member/info";
    }

    //접근 거부 페이지
    @GetMapping("/denied")
    public String deniedView() {
        return "member/denied";
    }

}
