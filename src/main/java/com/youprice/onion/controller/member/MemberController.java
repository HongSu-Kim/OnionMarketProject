package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.MemberJoinDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.security.validator.CustomValidators;
import com.youprice.onion.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
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
/*

    //회원가입
    @PostMapping("/joinProc")
    public String joinProc(@Valid MemberJoinDTO memberJoinDTO, @RequestParam("profileImage") MultipartFile profileImage, Errors errors, Model model) throws IOException {

        if (errors.hasErrors()) {
            //회원가입 실패 시 입력 데이터 값을 유지
            model.addAttribute("memberJoinDTO", memberJoinDTO);
            model.addAttribute("profileImage", profileImage);

            //유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            //회원가입 페이지로 다시 리턴
            return "member/join";
        }
        memberService.saveMember(memberJoinDTO, profileImage);
        return "redirect:login";
    }
*/

    //회원가입
    @PostMapping("/joinProc")
    public String joinProc(@Valid MemberJoinDTO memberJoinDTO, Errors errors, Model model) throws IOException {

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
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception, Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception); //error와 exception을 model에 담아서 넘겨줌
        return "member/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "member/logout";
    }

    //회원정보 수정 - 수정할 회원 정보를 받아 model에 담음
    @GetMapping("/modify")
    public String modify(@LoginUser SessionDTO sessionDTO, Model model) {
        if (sessionDTO != null) {
            model.addAttribute("member", sessionDTO);
        }
        
        return "member/modify";
    }

    //관심 카테고리
    @RequestMapping(value = "/selcategory", method = RequestMethod.POST)
    @ResponseBody
    public void selCategory(@RequestParam(value = "categoryArrTest[]")List<String>categoryArr) {

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
