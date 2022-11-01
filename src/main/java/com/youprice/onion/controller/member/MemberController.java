package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.*;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.security.auth.CustomUserDetails;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.security.validator.CustomValidators;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.member.ProhibitionKeywordService;
import com.youprice.onion.util.AlertRedirect;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {

    private final MemberService memberService;

    private final CustomValidators.UserIdValidator userIdValidator;
    private final CustomValidators.NicknameValidator nicknameValidator;
    private final CustomValidators.EmailValidator emailValidator;
    private final ProhibitionKeywordService prohibitionKeywordService;
    private final BCryptPasswordEncoder passwordEncoder;

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

    //로그아웃 페이지
    @GetMapping("/logout")
    public String logout() {
        return "member/logout";
    }


    //회원정보 수정 - 수정할 회원 정보를 받아 model에 담음
    @GetMapping("/modify")
    public String modify(@LoginUser SessionDTO sessionDTO, Model model) {
        if (sessionDTO != null) {
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            model.addAttribute("memberDTO", memberDTO);
        }
        return "member/modify";
    }

    //회원정보 수정 전 비밀번호 확인 페이지
    @GetMapping("/preModify")
    public String preModifyView() {
        return "member/preModify";
    }

    //security 비밀번호를 CustomUserDetails에서 받아옴
    @PostMapping("/preModify")
    public String PreModify(Authentication auth, @RequestParam("preModifypwd") String preModifypwd, RedirectAttributes rttr) {
        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
        String pwd = customUserDetails.getPassword();
        if(passwordEncoder.matches(preModifypwd, pwd)) {
            return "redirect:/member/modify";
        }
        else {
            rttr.addFlashAttribute("msg", "msg");
            return "redirect:/member/preModify";
        }
    }

    //마이페이지
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/mypage")
    public String mypageView(@LoginUser SessionDTO sessionDTO,  Model model) {
        MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());

        if (sessionDTO != null) {
            model.addAttribute("memberDTO", memberDTO);
        }
        return "member/mypage";
    }

    @GetMapping("profile/{memberId}")
    public String profileView(@PathVariable("memberId") Long memberId, @LoginUser SessionDTO sessionDTO, Model model) {
        if (sessionDTO == null) return "redirect:/member/login";

        MemberDTO memberDTO = memberService.getMemberDTO(memberId, sessionDTO.getId());

        if (Objects.equals(sessionDTO.getId(), memberDTO.getId())) {
            return "redirect:/member/mypage";
        }
        model.addAttribute("memberDTO", memberDTO);
        return "member/profile";
    }

    //아이디 찾기
    @GetMapping("/findId")
    public String findIdView() {
        return "member/findIdView";
    }

    @PostMapping("/findId")
    public String findId(MemberFindDTO memberFindDTO, Model model, HttpServletResponse response)throws Exception {
        if (memberService.countId(memberFindDTO.getEmail()) == 0) {
//            model.addAttribute("msg", "존재하지 않는 사용자 입니다. 이메일을 다시 확인해 주세요.");
//            return "member/findIdView";
            return AlertRedirect.warningMessage(response, "/member/findId", "존재하지 않는 이메일 입니다. 다시 확인해 주세요.");
        } else {
            model.addAttribute("member", memberService.findId(memberFindDTO.getEmail()));
            return "member/findId";
        }
    }

    //비밀번호 찾기
    @GetMapping("/findPwd")
    public String findPwdView(@ModelAttribute("memberDTO") MemberDTO memberDTO) {
        return "member/findPwdView";
    }

    @PostMapping("/findPwd")
    public String findPwd(MemberDTO memberDTO, BindingResult bindingResult, HttpServletResponse response) throws Exception {

        if (bindingResult.hasErrors()) {
            return AlertRedirect.warningMessage(response, "/member/findPwd", "존재하지 않는 이메일 입니다. 다시 확인해 주세요.");
        }
       memberDTO = memberService.findPwd(memberDTO.getEmail());

        if (memberDTO == null) {
            return AlertRedirect.warningMessage(response, "/member/findPwd", "존재하지 않는 이메일 입니다. 다시 확인해 주세요.");
        }
        return "redirect:/member/login";
    }

    //회원탈퇴
    @GetMapping("/withdraw")
    public String withdrawView() {
        return "member/withdraw";
    }

    @PostMapping("/withdraw")
    public String withdraw(Authentication auth, @RequestParam("withdrawpwd") String withdrawpwd, HttpServletResponse response) throws Exception {
        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
        String pwd = customUserDetails.getPassword();
        if (passwordEncoder.matches(withdrawpwd, pwd)) {
            memberService.withdraw(((CustomUserDetails) auth.getPrincipal()).getUsername());
            return "redirect:/member/logout";

        } else {
            return AlertRedirect.warningMessage(response, "/member/withdraw", "비밀번호가 일치하지 않습니다. 다시 확인해 주세요.");
        }
    }

    //관심 카테고리
    @GetMapping("/category")
    public String categoryView() {
        return "member/category";
    }

    @PostMapping("category.do")
    public ModelAndView handledRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] categoryList = request.getParameterValues("category");

        System.out.println(categoryList[0]);
        System.out.println(categoryList[1]);
        System.out.println(categoryList[2]);

        return new ModelAndView("category", "category", categoryList);
    }

    //접근 거부 페이지
    @GetMapping("/denied")
    public String deniedView() {
        return "member/denied";
    }

}
