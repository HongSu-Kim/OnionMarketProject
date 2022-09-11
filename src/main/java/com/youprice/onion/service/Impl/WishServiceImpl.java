package com.youprice.onion.service.Impl;

import com.youprice.onion.repository.WishRepository;
import com.youprice.onion.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishServiceImpl implements WishService {

    private final WishRepository wishRepository;

}
