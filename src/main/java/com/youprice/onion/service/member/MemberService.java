package com.youprice.onion.service.member;

import com.youprice.onion.dto.member.*;
import org.springframework.validation.Errors;

import java.util.Map;

public interface MemberService {

    public Long saveMember(MemberJoinDTO memberJoinDTO);

    public Map<String, String> validateHandling(Errors errors);

    //회원정보 수정
    public void modify(MemberModifyDTO memberModifyDTO);

    public MemberDTO getMemberDTO(Long memberId);

    public MemberDTO findId(String email);

    public int countId(String email);

    public void withdraw(String userId);

    public MemberDTO findPwd(String email) throws Exception;

//    public void profileImageUpdate(Long memberId, MemberModifyDTO memberModifyDTO, MultipartFile memberImageName);
}
