package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.member.impl.MemberServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("member")
public class MemberController {

    private final MemberService memberService;

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

    //회원가입 처리
    @PostMapping("/join")
    public String join(MemberDTO memberDTO) {
        memberService.saveMember(memberDTO);
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
