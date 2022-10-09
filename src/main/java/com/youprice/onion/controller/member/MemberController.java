package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/home")
    public String homeView() {
        return "member/home";
    }

    @GetMapping("/login")
    public String loginView() {
        return "member/login";
    }

    @GetMapping("/join")
    public String joinView() {
        return "member/join";
    }

    @PostMapping("/join")
    public String join(MemberDTO memberDTO) {
        memberService.saveMember(memberDTO);
        return "redirect:/login";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/member/info")
    public String userInfoView() {
        return "member/user_info";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String adminView() {
        return "member/admin";
    }

    @GetMapping("/denied")
    public String deniedView() {
        return "member/denied";
    }

}
