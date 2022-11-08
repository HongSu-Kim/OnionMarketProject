package com.youprice.onion.service.member.impl;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.MemberJoinDTO;
import com.youprice.onion.dto.member.MemberModifyDTO;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.member.Role;
import com.youprice.onion.repository.board.ReviewRepository;
import com.youprice.onion.repository.member.BlockRepository;
import com.youprice.onion.repository.member.FollowRepository;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.service.member.MemberService;
import com.youprice.onion.util.ImageUtil;
import com.youprice.onion.util.MailUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final BlockRepository blockRepository;
    private final FollowRepository followRepository;
    private final ReviewRepository reviewRepository;

    @Value("$(file.path}")
    private String uploadFolder;

//    @Autowired
//    private final ModelMapper modelMapper;

    //회원가입
    @Override
    public Long saveMember(MemberJoinDTO memberJoinDTO) throws IOException {

        memberJoinDTO.setMemberImageName(ImageUtil.store(memberJoinDTO.getProfileImg(), "member"));

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
    @Override
    public void modify(MemberModifyDTO memberModifyDTO) {
        Member findById = memberRepository.findById(memberModifyDTO.getId()).orElseThrow(() ->
                 new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        Member findByEmail = memberRepository.findByEmail(memberModifyDTO.getEmail()).orElse(null);
        Member findByNickname = memberRepository.findByNickname(memberModifyDTO.getNickname()).orElse(null);

        if (findByEmail != null && findByEmail.getId() != memberModifyDTO.getId()) {
            throw  new IllegalArgumentException("이미 존재하는 이메일 입니다. 다시 입력해 주세요.");
        } else if (findByNickname != null && findByNickname.getId() != memberModifyDTO.getId()) {
            throw new IllegalArgumentException("이미 존재하는 닉네임 입니다. 다시 입력해 주세요.");
        } else {
            String encPwd = passwordEncoder.encode(memberModifyDTO.getPwd());
            findById.modify(encPwd, memberModifyDTO.getNickname(), memberModifyDTO.getTel(), memberModifyDTO.getPostcode(),
                    memberModifyDTO.getAddress(), memberModifyDTO.getDetailAddress(), memberModifyDTO.getExtraAddress(), memberModifyDTO.getEmail());
        }
    }

    //프로필 변경
    @Override
    public String modifyProfileImg(Long memberId, MultipartFile profileImg) throws IOException {
        Member member = memberRepository.findById(memberId).orElse(null);
        member.modifyProfileImg(ImageUtil.store(profileImg, "member"));
        return memberRepository.save(member).getMemberImageName();
    }

    //아이디 찾기
    @Override
    public MemberDTO findId(String email) {
        return memberRepository.findByEmail(email).map(MemberDTO::new).orElse(null);
    }

    @Override
    public int countId(String email) {
        return memberRepository.countByEmail(email);
    }

    //비밀번호 찾기
    @Override
    public MemberDTO findPwd(String email, String url) throws Exception {

        //회원정보 불러오기
        Member member = memberRepository.findByEmail(email).orElse(null);

        if (member == null) {
            return null;
        }
        //이메일 전송

        //임시 비밀번호 생성
        String tempPwd = UUID.randomUUID().toString().replace("-", "");
        tempPwd = tempPwd.substring(0, 10);

        member.findPwd(tempPwd);

        //이메일 전송
        MailUtil.sendMail(member, url);

        //암호화된 임시 비밀번호 저장
        member.findPwd(passwordEncoder.encode(member.getPwd()));
        memberRepository.save(member);

        return new MemberDTO(member);
    }

	@Override
	@Transactional(readOnly = true)
	public List<MemberDTO> getChatMemberList(Long memberId) {
		return memberRepository.findAllChatMember(memberId).stream().map(MemberDTO::new).collect(Collectors.toList());
	}

    @Override
    public void withdraw(String userId) {
        Member member = memberRepository.findByUserId(userId).orElse(null);
        member.changeRole(Role.WITHDRAWAL);
        memberRepository.save(member);
    }

    @Override
    public MemberDTO getMemberDTO(Long memberId) {
        return memberRepository.findById(memberId).map(MemberDTO::new).orElse(null);
    }

    @Override
    public MemberDTO getMemberDTO(Long memberId, Long sessionId) { //memberId: 타겟유저, sessionId: 로그인 한 유저
        return memberRepository.findById(memberId).map(member -> {
            MemberDTO memberDTO = new MemberDTO(member);

            memberDTO.setFollowCheck(followRepository.existsByMemberIdAndTargetId(sessionId, memberId));
            memberDTO.setBlockCheck(blockRepository.existsByMemberIdAndTargetId(sessionId, memberId));

            return memberDTO;
        }).orElse(null);
    }

    //양파페이 충전
    @Override
    public int chargeCash(Long memberId, int amount) {
        Member member = memberRepository.findById(memberId).get();
        member.addCash(amount);
		return member.getCash();
    }

    @Override
    public Page<MemberDTO> getMemberList(Pageable pageable) {
        return memberRepository.findAll(pageable).map(MemberDTO::new);
    }

    @Override
    public void accountLock(Long targetId) {
        Member target = memberRepository.findById(targetId).orElse(null);
        if (target == null) return;

        target.changeRole(Role.LOCK);
        memberRepository.save(target);
    }

    @Override
    public void removeLock(Long targetId) {
        Member target = memberRepository.findById(targetId).orElse(null);
        if (target == null) return;

        target.changeRole(Role.USER);
        memberRepository.save(target);
    }
}
