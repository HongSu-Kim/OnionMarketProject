package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.BlockListDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.member.Blockservice;
import com.youprice.onion.util.AlertRedirect;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("block")
public class BlockController {

    private final Blockservice blockService;

    //팔로우 목록 페이지
    @GetMapping("list")
    public String BlockList(@LoginUser SessionDTO sessionDTO, Model model, @PageableDefault Pageable pageable) {
        if (sessionDTO == null)
            return "redirect:/member/login";

        Page<BlockListDTO> page = blockService.getBlockList(sessionDTO.getId(), pageable);

        model.addAttribute("page", page);
        return "member/blockList";
    }

    //팔로우 추가
    @GetMapping("addBlock/{targetId}")
    public String addBlockGet(@LoginUser SessionDTO sessionDTO, @PathVariable Long targetId, HttpServletResponse response) throws IOException {
        if (sessionDTO == null) return "redirect:/member/login";

        try {
            blockService.addBlock(sessionDTO.getId(), targetId);
        } catch (Exception e) {
            return AlertRedirect.warningMessage(response, "/", "회원이 존재하지 않습니다.");
        }

        return AlertRedirect.warningMessage(response, "/member/profile/" + targetId, "해당 회원을 차단했습니다.");
    }

    //팔로우 삭제
    @GetMapping("removeBlock/{targetId}")
    @ResponseBody
    public String removeBlockGet(@LoginUser SessionDTO sessionDTO, @RequestParam Long targetId, HttpServletResponse response) throws IOException {
        if (sessionDTO == null) return "redirect:/member/login";

        blockService.removeBlock(sessionDTO.getId(), targetId);

        return AlertRedirect.warningMessage(response, "/member/profile/" + targetId, "해당 회원을 차단 해제했습니다.");
    }

}
