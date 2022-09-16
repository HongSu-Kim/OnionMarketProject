package com.youprice.onion.controller;

import com.youprice.onion.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

}
