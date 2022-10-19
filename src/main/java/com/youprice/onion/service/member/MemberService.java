package com.youprice.onion.service.member;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.MemberJoinDTO;
import com.youprice.onion.dto.member.MemberModifyDTO;
import com.youprice.onion.dto.member.SessionDTO;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface MemberService {

    public Long saveMember(MemberJoinDTO memberJoinDTO);

    public Map<String, String> validateHandling(Errors errors);

    //회원정보 수정
    public boolean modify(MemberModifyDTO memberModifyDTO);

    public MemberDTO getMemberDTO(Long memberId);

    public void profileImageUpdate(Long memberId, MemberModifyDTO memberModifyDTO, MultipartFile memberImageName);
}
