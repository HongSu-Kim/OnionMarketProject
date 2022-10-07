package com.youprice.onion.service.order.impl;

import com.youprice.onion.repository.order.WishRepository;
import com.youprice.onion.service.order.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class WishServiceImpl implements WishService {

    private final WishRepository wishRepository;

}
