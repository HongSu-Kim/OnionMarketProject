package com.youprice.onion.service.member.impl;

import com.youprice.onion.dto.member.*;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.repository.member.BlockRepository;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final BlockRepository blockRepository;

    @Value("$(file.path}")
    private String uploadFolder;

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

    //회원정보 수정
    @Transactional
    @Override
    public boolean modify(MemberModifyDTO memberModifyDTO) {
        Member member = memberRepository.findById(memberModifyDTO.getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        Member member1 = memberRepository.findByNickname(memberModifyDTO.getNickname()).orElse(null);

        if (member1 == null || member1.getId() == memberModifyDTO.getId()) {
            String encPwd = passwordEncoder.encode(memberModifyDTO.getPwd());
            member.modify(encPwd, memberModifyDTO.getNickname(), memberModifyDTO.getTel(), memberModifyDTO.getPostcode(), memberModifyDTO.getAddress(), memberModifyDTO.getDetailAddress(), memberModifyDTO.getExtraAddress(), memberModifyDTO.getEmail(), memberModifyDTO.getMemberImageName());
            return true;
        } else {
            return false;
        }
    }

    //아이디 찾기
    @Override
    public List<Member> findId(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    public int countId(String email) {
        return memberRepository.countByEmail(email);
    }

    //비밀번호 찾기 메일 생성
    @Override
    public MailDTO createEmail(String email) {
        String str = getTempPwd();
        MailDTO mailDTO = new MailDTO();
        mailDTO.setEmail(email);
        mailDTO.setTitle("양파마켓 임시 비밀번호 이메일 입니다.");
        mailDTO.setMessage("안녕하세요, 양파마켓 임시 비밀번호 안내 이메일 입니다." + "회원님의 임시 비밀번호는 [ " + str + " ] 입니다." + "로그인 후에 반드시 비밀번호를 변경해 주시기 바랍니다.");


        return mailDTO;
    }

    //임시 비밀번호 생성
    @Override
    public String getTempPwd() {
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };
        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
    //메일 보내기


    //기존 비밀번호를 임시 비밀번호로 업데이트
/*    @Override
    public void updatePwd(String str, MemberFindDTO memberFindDTO) {
        String pwd = str;
        memberRepository.findByEmail(memberFindDTO.getEmail())
    }*/

    @Override
    public MemberDTO getMemberDTO(Long memberId) {
        return memberRepository.findById(memberId).map(MemberDTO::new).orElse(null);
    }
/*
    //프로필사진 수정
    @Transactional
    @Override
    public void profileImageUpdate(Long memberId, MemberModifyDTO memberModifyDTO, MultipartFile profileImageFile) {
        UUID uuid = UUID.randomUUID();
        String memberImageName = uuid + "_" + profileImageFile.getOriginalFilename();
        Path imageFilePath = Paths.get(uploadFolder + memberImageName);

        try {
            Files.write(imageFilePath, profileImageFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Member member = memberRepository.findById(memberId).orElseThrow(() -> {
            return new IllegalArgumentException("프로필 사진 수정 실패 : 존재하지 않는 회원입니다.");
        });
    }
*/

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
