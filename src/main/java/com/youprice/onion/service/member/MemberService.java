package com.youprice.onion.service.member;

import com.youprice.onion.dto.member.*;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface MemberService {

    public Long saveMember(MemberJoinDTO memberJoinDTO) throws IOException;

    public Map<String, String> validateHandling(Errors errors);

    //회원정보 수정
    public void modify(MemberModifyDTO memberModifyDTO);

    public MemberDTO getMemberDTO(Long memberId);

    public MemberDTO getMemberDTO(Long memberId, Long sessionId);

    public MemberDTO findId(String email);

    public int countId(String email);

    public void withdraw(String userId);

    public MemberDTO findPwd(String email) throws Exception;

    void modifyProfileImg(Long memberId, MultipartFile profileImg) throws IOException;

//    public void profileImageUpdate(Long memberId, MemberModifyDTO memberModifyDTO, MultipartFile memberImageName);
}
