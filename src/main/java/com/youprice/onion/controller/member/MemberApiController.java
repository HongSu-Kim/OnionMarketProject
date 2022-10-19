package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.MemberModifyDTO;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.security.auth.LoginUser;
import com.youprice.onion.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberApiController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;

    @PutMapping("/member")
    public ResponseEntity<String> modify(@RequestBody MemberModifyDTO memberModifyDTO) {

        if (!memberService.modify(memberModifyDTO)){
            return new ResponseEntity<>("success", HttpStatus.BAD_REQUEST);
        }
        //변경된 세션 등록
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(memberModifyDTO.getUserId(), memberModifyDTO.getPwd()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}