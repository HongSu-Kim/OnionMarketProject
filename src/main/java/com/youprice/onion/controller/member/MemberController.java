package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.KeywordListDTO;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.MemberJoinDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.security.validator.CustomValidators;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.member.ProhibitionKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private final ProhibitionKeywordService prohibitionKeywordService;

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

    //회원가입(프로필사진)
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
    public String joinProc(@Valid MemberJoinDTO memberJoinDTO, Errors errors, Model model, BindingResult bindingResult) throws IOException {

        if (prohibitionKeywordService.ProhibitionKeywordFind(memberJoinDTO.getNickname())) { //금지키워가있으면 true
            bindingResult.addError(new FieldError("memberJoinDTO", "nickname", "적합하지 않은 단어가 포함되어 있습니다."));

            if (bindingResult.hasErrors()) {
                return "member/join";
            }
        }

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
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            model.addAttribute("member", memberDTO.getId());
            model.addAttribute("memberDTO", memberDTO);
        }
        return "member/modify";
    }

    //관심 카테고리
    @GetMapping("/category")
    public String categoryView() {
        return "member/category";
    }

    public ModelAndView handledRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] categoryList = request.getParameterValues("category");
        return new ModelAndView("category", "category", categoryList);
    }
/*
    @RequestMapping(value = "welcomeWeb4.do")
    public String categoryList(HttpServletRequest request, ModelMap model) throws Exception {
        String[] category = request.getParameterValues("category");
        model.addAttribute("category", category);
        return "member/category";
    }
*/

    //마이페이지
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/mypage")
    public String mypageView(@LoginUser SessionDTO sessionDTO, Model model) {
        if (sessionDTO != null) {
            model.addAttribute("session", sessionDTO.getId());
            model.addAttribute("sessionDTO", sessionDTO);
        }
        return "member/mypage";
    }

    //접근 거부 페이지
    @GetMapping("/denied")
    public String deniedView() {
        return "member/denied";
    }

}
