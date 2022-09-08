package com.youprice.onion.controller;

import com.youprice.onion.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

}
