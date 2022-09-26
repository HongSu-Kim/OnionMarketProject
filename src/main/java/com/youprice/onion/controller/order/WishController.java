package com.youprice.onion.controller.order;

import com.youprice.onion.service.order.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;

}
