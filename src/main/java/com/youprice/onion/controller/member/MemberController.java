package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.*;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.security.validator.CustomValidators;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.member.ProhibitionKeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    //회원가입
    @PostMapping("/joinProc")
    public String joinProc(@Valid MemberJoinDTO memberJoinDTO, Errors errors, Model model, BindingResult bindingResult) {

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
            model.addAttribute("memberDTO", memberDTO);
        }
        return "member/modify";
    }

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

    //아이디 찾기
    @GetMapping("/findIdView")
    public String findIdView() {
        return "member/findIdView";
    }

    @PostMapping("findId")
    public String findId(MemberFindDTO memberFindDTO, Model model) {
        if (memberService.countId(memberFindDTO.getEmail()) == 0) {
            model.addAttribute("msg", "존재하지 않는 사용자 입니다. 이메일을 다시 확인해 주세요.");
            return "member/findIdView";
        } else {
            model.addAttribute("member", memberService.findId(memberFindDTO.getEmail()));
            return "member/findId";
        }
    }

/*    //임시 비밀번호 이메일 보내기
    @Transactional
    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam("email") String email) {
        MailDTO mailDTO = memberService.createMail
    }*/

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
