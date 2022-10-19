package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.MemberModifyDTO;
import com.youprice.onion.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberApiController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;

    @PutMapping("/member")
    public ResponseEntity<String> modify(@RequestBody MemberModifyDTO memberModifyDTO) {
        memberService.modify(memberModifyDTO);
        //변경된 세션 등록
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(memberModifyDTO.getUserId(), memberModifyDTO.getPwd()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}