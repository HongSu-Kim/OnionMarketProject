package com.youprice.onion.controller.product;

import com.youprice.onion.service.product.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("auction")
public class AuctionController {

    private final AuctionService auctionService;


}
