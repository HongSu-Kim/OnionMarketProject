package com.youprice.onion.service.Impl;

import com.youprice.onion.mapper.MemberMapper;
import com.youprice.onion.repository.MemberRepository;
import com.youprice.onion.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

}
