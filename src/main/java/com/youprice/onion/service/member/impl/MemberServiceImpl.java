package com.youprice.onion.service.member.impl;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.MemberJoinDTO;
import com.youprice.onion.dto.member.MemberModifyDTO;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.repository.member.BlockRepository;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final BlockRepository blockRepository;

    @Autowired
    private final ModelMapper modelMapper;

    //회원가입
    @Transactional
    @Override
    public Long saveMember(MemberJoinDTO memberJoinDTO) {
        memberJoinDTO.setPwd(passwordEncoder.encode(memberJoinDTO.getPwd())); //패스워드 암호화 저장
        return memberRepository.save(memberJoinDTO.toEntity()).getId();
    }

    //회원가입 시 유효성 및 중복 체크
    //유효성 검사에 실패한 필드는 key 값과 에러 메시지를 응답
    //Key : valid_{dto 필드명}
    //Message : dto에서 작성한 message 값
    @Transactional(readOnly = true)
    @Override
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        //유효성 검사에 실패한 필드 목록을 받음
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    @Transactional
    @Override
    public void checkUserIdDuplication(MemberDTO memberDTO) {
        boolean userIdDuplicate = memberRepository.existsByUserId(memberDTO.toEntity().getUserId());
        if (userIdDuplicate) {
            throw new IllegalStateException("이미 존재하는 아이디 입니다.");
        }
    }

    @Transactional
    @Override
    public void checkNicknameDuplication(MemberDTO memberDTO) {
        boolean nicknameDuplicate = memberRepository.existsByNickname(memberDTO.toEntity().getNickname());
        if (nicknameDuplicate) {
            throw new IllegalStateException("이미 존재하는 닉네임 입니다.");
        }
    }

    @Transactional
    @Override
    public void checkEmailDuplication(MemberDTO memberDTO) {
        boolean emailDuplicate = memberRepository.existsByEmail(memberDTO.toEntity().getEmail());
        if (emailDuplicate) {
            throw new IllegalStateException("이미 존재하는 이메일 입니다.");
        }
    }

    //회원정보 수정
    @Transactional
    @Override
    public void modify(MemberModifyDTO memberModifyDTO) {
        Member member = memberRepository.findById(memberModifyDTO.toEntity().getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        String encPwd = passwordEncoder.encode(memberModifyDTO.getPwd());
        member.modify(encPwd, memberModifyDTO.getNickname(), memberModifyDTO.getTel(), memberModifyDTO.getPostcode(), memberModifyDTO.getAddress(), memberModifyDTO.getDetailAddress(), memberModifyDTO.getExtraAddress(), memberModifyDTO.getEmail(), memberModifyDTO.getMemberImageName());

    }

    @Override
    public MemberDTO getMemberDTO(Long memberId) {
        return memberRepository.findById(memberId).map(MemberDTO::new).orElse(null);
    }

/*  차단
    @Override
    public MemberDTO getMemberDTO(Long memberId) {

        Long sender = 1L;
        Long target = 2L;
        if (blockRepository.existsBlockByMemberAndTarget(target,sender)) {
            // 차단되는 로직
            throw new NullPointerException();
        }
        // 채팅로직
        return null;
    }

    //차단 추가
    public void makeBlock(Long memberId, Long targetId) {

        Member targetingMember = memberRepository.findById(memberId).orElseThrow(NullPointerException::new);
        Member targetedMember = memberRepository.findById(targetId).orElseThrow(NullPointerException::new);

        Block block = Block.builder()
                .member(targetingMember)
                .target(targetedMember)
                .build();

        blockRepository.save(block);
    }
*/

}
