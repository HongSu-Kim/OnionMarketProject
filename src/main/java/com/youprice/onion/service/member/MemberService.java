package com.youprice.onion.service.member;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.MemberJoinDTO;
import org.springframework.validation.Errors;

import java.util.Map;

public interface MemberService {

    public Long saveMember(MemberJoinDTO memberJoinDTO);

    public Map<String, String> validateHandling(Errors errors);

    //userId, nickname, email 중복 여부 확인
    public void checkUserIdDuplication(MemberDTO memberDTO);
    public void checkNicknameDuplication(MemberDTO memberDTO);
    public void checkEmailDuplication(MemberDTO memberDTO);

    public MemberDTO getMemberDTO(Long memberId);

}
