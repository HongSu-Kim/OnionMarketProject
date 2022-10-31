package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.FollowDTO;
import com.youprice.onion.dto.member.FollowListDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.member.FollowService;
import com.youprice.onion.util.AlertRedirect;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("follow")
public class FollowController {

    private final FollowService followService;

    //팔로우 목록 페이지
    @GetMapping("list")
    public String followList(@LoginUser SessionDTO sessionDTO, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        if (sessionDTO == null)
            return "redirect:/member/login";

        Page<FollowDTO> page = followService.getFollowList(sessionDTO.getId(), pageable);

        model.addAttribute("page", page);
        return "member/followList";
    }

    //팔로우 추가
    @GetMapping("addFollow/{targetId}")
    public String addFollowGet(@LoginUser SessionDTO sessionDTO, @PathVariable Long targetId, HttpServletResponse response) throws IOException {
        if (sessionDTO == null) return "redirect:/member/login";

        try {
            followService.addFollow(sessionDTO.getId(), targetId);
        } catch (Exception e) {
            return AlertRedirect.warningMessage(response, "/", "회원이 존재하지 않습니다.");
        }

        return AlertRedirect.warningMessage(response, "/follow/list/", "해당 회원을 팔로우 했습니다.");
//        return AlertRedirect.warningMessage(response, "/member/profile/" + targetId, "해당 회원을 팔로우 했습니다.");
    }

    //팔로우 삭제
    @GetMapping("removeFollow/{targetId}")
    @ResponseBody
    public String removeFollowGet(@LoginUser SessionDTO sessionDTO, @PathVariable Long targetId, HttpServletResponse response) throws IOException {
        if (sessionDTO == null) return "redirect:/member/login";

        followService.removeFollow(sessionDTO.getId(), targetId);

        return AlertRedirect.warningMessage(response, "/follow/list/", "해당 회원을 언팔로우 했습니다.");
//        return AlertRedirect.warningMessage(response, "/member/profile/" + targetId, "해당 회원을 언팔로우 했습니다.");
    }

}
