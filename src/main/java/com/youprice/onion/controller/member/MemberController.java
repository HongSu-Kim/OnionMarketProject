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

    //???????????? ??? ????????? ????????? ??????
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(userIdValidator);
        binder.addValidators(nicknameValidator);
        binder.addValidators(emailValidator);
    }

    //???????????? ?????????
    @GetMapping("/join")
    public String joinView() {
        return "member/join";
    }

    //????????????
    @PostMapping("/joinProc")
    public String joinProc(@Valid MemberJoinDTO memberJoinDTO, Errors errors, Model model, BindingResult bindingResult) throws IOException {

        if (errors.hasErrors()) {
            //???????????? ?????? ??? ?????? ????????? ?????? ??????
            model.addAttribute("memberJoinDTO", memberJoinDTO);

            //????????? ?????? ?????? ????????? ???????????? ?????????
            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            //???????????? ???????????? ?????? ??????
            return "member/join";
        }

        if (prohibitionKeywordService.ProhibitionKeywordFind(memberJoinDTO.getNickname())) { //???????????????????????? true
            bindingResult.addError(new FieldError("memberJoinDTO", "nickname", "???????????? ?????? ????????? ???????????? ????????????."));

            if (bindingResult.hasErrors()) {
                return "member/join";
            }
        }

        memberService.saveMember(memberJoinDTO);
        return "redirect:login";
    }

    //????????? ?????????
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception, Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception); //error??? exception??? model??? ????????? ?????????
        return "member/login";
    }

    //???????????? ?????????
    @GetMapping("/logout")
    public String logout() {
        return "member/logout";
    }


    //???????????? ?????? - ????????? ?????? ????????? ?????? model??? ??????
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify")
    public String modify(@LoginUser SessionDTO sessionDTO, Model model) {
        if (sessionDTO != null) {
            MemberDTO memberDTO = memberService.getMemberDTO(sessionDTO.getId());
            model.addAttribute("memberDTO", memberDTO);
        }
        return "member/modify";
    }

    //????????? ?????? ??????
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

    //???????????? ?????? ??? ???????????? ?????? ?????????
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/preModify")
    public String preModifyView() {
        return "member/preModify";
    }

    //security ??????????????? CustomUserDetails?????? ?????????
    @PostMapping("/preModify")
    public String PreModify(Authentication auth, @RequestParam("preModifypwd") String preModifypwd, HttpServletResponse response) throws IOException {
        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();
        String pwd = customUserDetails.getPassword();
        if(passwordEncoder.matches(preModifypwd, pwd)) {
            return "redirect:/member/modify";
        }
        else {
            AlertRedirect.warningMessage(response, "??????????????? ???????????? ????????????. ?????? ????????? ?????????");
            return null;
        }
    }

    //???????????????
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

    //????????? ??????
    @GetMapping("/findId")
    public String findIdView() {
        return "member/findIdView";
    }

    @PostMapping("/findId")
    public String findId(MemberFindDTO memberFindDTO, Model model, HttpServletResponse response)throws Exception {
        if (memberService.countId(memberFindDTO.getEmail()) == 0) {
//            model.addAttribute("msg", "???????????? ?????? ????????? ?????????. ???????????? ?????? ????????? ?????????.");
//            return "member/findIdView";
            return AlertRedirect.warningMessage(response, "/member/findId", "???????????? ?????? ????????? ?????????. ?????? ????????? ?????????.");
        } else {
            model.addAttribute("member", memberService.findId(memberFindDTO.getEmail()));
            return "member/findId";
        }
    }

    //???????????? ??????
    @GetMapping("/findPwd")
    public String findPwdView(@ModelAttribute("memberDTO") MemberDTO memberDTO) {
        return "member/findPwdView";
    }

    @PostMapping("/findPwd")
    public String findPwd(MemberDTO memberDTO, BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {

        if (bindingResult.hasErrors()) {
            return AlertRedirect.warningMessage(response, "/member/findPwd", "???????????? ?????? ????????? ?????????. ?????? ????????? ?????????.");
        }

		String requestURL = request.getRequestURL().toString();
		String url = requestURL.substring(0, requestURL.indexOf("/", 10));

		memberDTO = memberService.findPwd(memberDTO.getEmail(), url);

        if (memberDTO == null) {
            return AlertRedirect.warningMessage(response, "/member/findPwd", "???????????? ?????? ????????? ?????????. ?????? ????????? ?????????.");
        }
        return "redirect:/member/login";
    }

    //????????????
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
            return AlertRedirect.warningMessage(response, "/member/withdraw", "??????????????? ???????????? ????????????. ?????? ????????? ?????????.");
        }
    }

    //?????? ????????????
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

    //???????????? ?????? ?????????
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

    //??????????????? - ???????????? ?????????
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/manageMember")
    public String memberList(@LoginUser SessionDTO sessionDTO, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable, HttpServletResponse response) throws IOException {
        if (!sessionDTO.getRole().equals(Role.ADMIN))
            return AlertRedirect.warningMessage(response, "/", "???????????? ?????? ????????? ????????? ?????????.");

        Page<MemberDTO> page = memberService.getMemberList(pageable);

        model.addAttribute("page", page);
        return "member/manageMember.admin";
    }

    //?????? ??????
    @PreAuthorize("isAuthenticated()")
    @GetMapping("addLock/{targetId}")
    public String addLockGet(@LoginUser SessionDTO sessionDTO, @PathVariable Long targetId, HttpServletResponse response) throws IOException {
        if (!sessionDTO.getRole().equals(Role.ADMIN))
            return AlertRedirect.warningMessage(response, "/", "???????????? ?????? ????????? ????????? ?????????.");

        try {
            memberService.accountLock(targetId);
        } catch (Exception e) {
            return AlertRedirect.warningMessage(response, "/", "????????? ???????????? ????????????.");
        }

        return AlertRedirect.warningMessage(response, "/member/manageMember/", "?????? ????????? ????????? ?????? ??????????????????.");
    }

    //?????? ?????? ??????
    @PreAuthorize("isAuthenticated()")
    @GetMapping("removeLock/{targetId}")
    public String removeLockGet(@LoginUser SessionDTO sessionDTO, @PathVariable Long targetId, HttpServletResponse response) throws IOException {
        if (!sessionDTO.getRole().equals(Role.ADMIN))
            return AlertRedirect.warningMessage(response, "/", "???????????? ?????? ????????? ????????? ?????????.");

        memberService.removeLock(targetId);

        return AlertRedirect.warningMessage(response, "/member/manageMember/", "?????? ????????? ????????? ?????? ?????????????????????.");
    }
}
