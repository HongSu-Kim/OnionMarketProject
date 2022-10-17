package com.youprice.onion.controller.member;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class MemberApiController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;

    @PutMapping("/member")
    public ResponseEntity<String> modify(@RequestBody MemberDTO memberDTO/*, @RequestParam("modifyProfileImage") MultipartFile modifyProfileImage*/) throws IOException {
        memberService.modify(memberDTO/*, modifyProfileImage*/);

        //변경된 세션 등록
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(memberDTO.getUserId(), memberDTO.getPwd()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
