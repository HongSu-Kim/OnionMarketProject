package com.youprice.onion.service.member.impl;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.member.Role;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public Long saveMember(MemberDTO memberDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDTO.setPwd(passwordEncoder.encode(memberDTO.getPwd())); //패스워드 암호화 저장
        if (memberDTO.getUserId() != "admin") {
            memberDTO.setRole(Role.valueOf("USER"));
        } else {
            memberDTO.setRole(Role.valueOf("ADMIN"));
        }

        return memberRepository.save(memberDTO.toEntity()).getId();
    }

    @Override
    public MemberDTO getMemberDTO(Long memberId) {
        return modelMapper.map(memberRepository.findById(memberId).orElse(null), MemberDTO.class);
    }

/*

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    private final BCryptPasswordEncoder encoder;

    //회원가입
    @Override
    public Long memberJoin(MemberDTO.RequestMemberDTO dto) {
        //비밀번호 암호화
        dto.encryptPassword(encoder.encode(dto.getPwd()));

        Member member = dto.toEntity();
        memberRepository.save(member);
        log.info("회원가입 성공");

        return member.getId();
    }

    @Override
    public Long memberInfoModify(MemberDTO.RequestMemberDTO dto) {
        return null;
    }

    @Override
    public boolean checkUserIdDuplication(String userId) {
        boolean userIdDuplicate = memberRepository.existsByUserId(userId);
        return userIdDuplicate;
    }

    @Override
    public boolean checkNicknameDuplication(String nickname) {
        boolean nicknameDuplicate = memberRepository.existsByNickname(nickname);
        return nicknameDuplicate;
    }

    @Override
    public boolean checkEmailDuplication(String email) {
        boolean emailDuplicate = memberRepository.existsByEmail(email);
        return emailDuplicate;
    }
*/

}
