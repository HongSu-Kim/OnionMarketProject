package com.youprice.onion.service.member;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.MemberJoinDTO;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface MemberService {

//    public Long saveMember(MemberJoinDTO memberJoinDTO, MultipartFile profileImage) throws IOException;
    public Long saveMember(MemberJoinDTO memberJoinDTO) throws IOException;

    public Map<String, String> validateHandling(Errors errors);

    //userId, nickname, email 중복 여부 확인
    public void checkUserIdDuplication(MemberDTO memberDTO);
    public void checkNicknameDuplication(MemberDTO memberDTO);
    public void checkEmailDuplication(MemberDTO memberDTO);

    //회원정보 수정
    public void modify(MemberDTO memberDTO, MultipartFile modifyProfileImage) throws IOException;

    public MemberDTO getMemberDTO(Long memberId);

}
