package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.MemberFindDTO;
import com.youprice.onion.dto.member.MemberJoinDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.entity.member.Role;
import com.youprice.onion.security.auth.CustomUserDetails;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.security.validator.CustomValidators;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.service.member.ProhibitionKeywordService;
import com.youprice.onion.util.AlertRedirect;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
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

    //회원가입 페이지
    @GetMapping("/join")
    public String joinView() {
        return "member/join";
    }

    //회원가입
    @PostMapping("/joinProc")
    public String joinProc(@Valid MemberJoinDTO memberJoinDTO, Errors errors, Model model, BindingResult bindingResult) throws IOException {

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

        if (prohibitionKeywordService.ProhibitionKeywordFind(memberJoinDTO.getNickname())) { //금지키워가있으면 true
            bindingResult.addError(new FieldError("memberJoinDTO", "nickname", "적합하지 않은 단어가 포함되어 있습니다."));

            if (bindingResult.hasErrors()) {
                return "member/join";
            }
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
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify")
    public String modify(@LoginUser SessionDTO sessionDTO, Model model) {
        if (sessionDTO != null) {
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            model.addAttribute("memberDTO", memberDTO);
        }
        return "member/modify";
    }

    //프로필 사진 수정
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modifyProfileImg")
    public String modifyProfileImgView() {
        return "member/modifyProfileImg";
    }

    @PostMapping("modifyProfileImg")
    public String modifyProfileImg(@LoginUser SessionDTO sessionDTO, HttpSession session, MultipartFile profileImg) throws IOException {
        String memberImageName = memberService.modifyProfileImg(sessionDTO.getId(), profileImg);
        session.setAttribute("memberImageName", memberImageName);

        return "redirect:/member/mypage";
    }

    //회원정보 수정 전 비밀번호 확인 페이지
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/preModify")
    public String preModifyView() {
        return "member/preModify";
    }

    //security 비밀번호를 CustomUserDetails에서 받아옴
    @PostMapping("/preModify")
    public String PreModify(Authentication auth, @RequestParam("preModifypwd") String preModifypwd, HttpServletResponse response) throws IOException {
        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
        String pwd = customUserDetails.getPassword();
        if(passwordEncoder.matches(preModifypwd, pwd)) {
            return "redirect:/member/modify";
        }
        else {
            AlertRedirect.warningMessage(response, "비밀번호가 일치하지 않습니다. 다시 확인해 주세요");
            return null;
        }
    }

    //마이페이지
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/mypage")
    public String mypageView(@LoginUser SessionDTO sessionDTO,  Model model) {
        MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());

        model.addAttribute("memberDTO", memberDTO);
        return "member/mypage";
    }

    @PreAuthorize("isAuthenticated()")
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
    public String findPwd(MemberDTO memberDTO, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {

        if (bindingResult.hasErrors()) {
            return AlertRedirect.warningMessage(response, "/member/findPwd", "존재하지 않는 이메일 입니다. 다시 확인해 주세요.");
        }

		String requestURL = request.getRequestURL().toString();
		String url = requestURL.substring(0, requestURL.indexOf("/", 10));

		memberDTO = memberService.findPwd(memberDTO.getEmail(), url);

        if (memberDTO == null) {
            return AlertRedirect.warningMessage(response, "/member/findPwd", "존재하지 않는 이메일 입니다. 다시 확인해 주세요.");
        }
        return "redirect:/member/login";
    }

    //회원탈퇴
    @PreAuthorize("isAuthenticated()")
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
    @PreAuthorize("isAuthenticated()")
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

    @PreAuthorize("isAuthenticated()")
	@GetMapping("chatMemberList")
	@ResponseBody
	public ResponseEntity chatMemberList(@LoginUser SessionDTO sessionDTO) {
		if (sessionDTO == null) new ResponseEntity<>("/member/login", HttpStatus.UNAUTHORIZED);

		List<MemberDTO> memberDTOList = memberService.getChatMemberList(sessionDTO.getId());

		return new ResponseEntity<>(memberDTOList, HttpStatus.OK);
	}

    //양파페이 충전 페이지
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/cash/{memberId}")
    public String myCash(@LoginUser SessionDTO sessionDTO, @PathVariable("memberId") Long memberId, Model model) {
        if (sessionDTO == null) return "redirect:/member/login";

        MemberDTO memberDTO = memberService.getMemberDTO(memberId);
        model.addAttribute("memberDTO", memberDTO);

        return "member/cashCharge";
    }

    @PostMapping("/charge/cash")
    @ResponseBody
    public String chargeCash(@LoginUser SessionDTO sessionDTO, @RequestParam int amount, HttpSession session) {
        MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
		int cash = memberService.chargeCash(memberDTO.getId(), amount);
		session.setAttribute("cash", cash);
        return "/member/mypage";
    }

    //관리자권한 - 회원관리 페이지
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/manageMember")
    public String memberList(@LoginUser SessionDTO sessionDTO, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable, HttpServletResponse response) throws IOException {
        if (!sessionDTO.getRole().equals(Role.ADMIN))
            return AlertRedirect.warningMessage(response, "/", "관리자만 접근 가능한 페이지 입니다.");

        Page<MemberDTO> page = memberService.getMemberList(pageable);

        model.addAttribute("page", page);
        return "member/manageMember.admin";
    }

    //계정 잠금
    @PreAuthorize("isAuthenticated()")
    @GetMapping("addLock/{targetId}")
    public String addLockGet(@LoginUser SessionDTO sessionDTO, @PathVariable Long targetId, HttpServletResponse response) throws IOException {
        if (!sessionDTO.getRole().equals(Role.ADMIN))
            return AlertRedirect.warningMessage(response, "/", "관리자만 접근 가능한 페이지 입니다.");

        try {
            memberService.accountLock(targetId);
        } catch (Exception e) {
            return AlertRedirect.warningMessage(response, "/", "회원이 존재하지 않습니다.");
        }

        return AlertRedirect.warningMessage(response, "/member/manageMember/", "해당 회원의 계정을 일시 잠금했습니다.");
    }

    //계정 잠금 해제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("removeLock/{targetId}")
    public String removeLockGet(@LoginUser SessionDTO sessionDTO, @PathVariable Long targetId, HttpServletResponse response) throws IOException {
        if (!sessionDTO.getRole().equals(Role.ADMIN))
            return AlertRedirect.warningMessage(response, "/", "관리자만 접근 가능한 페이지 입니다.");

        memberService.removeLock(targetId);

        return AlertRedirect.warningMessage(response, "/member/manageMember/", "해당 회원의 계정을 잠금 해제하였습니다.");
    }
}
