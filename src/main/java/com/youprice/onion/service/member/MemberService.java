package com.youprice.onion.service.member;

import com.youprice.onion.dto.member.MailDTO;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.MemberJoinDTO;
import com.youprice.onion.dto.member.MemberModifyDTO;
import com.youprice.onion.entity.member.Member;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface MemberService {

    public Long saveMember(MemberJoinDTO memberJoinDTO);

    public Map<String, String> validateHandling(Errors errors);

    //회원정보 수정
    public boolean modify(MemberModifyDTO memberModifyDTO);

    public MemberDTO getMemberDTO(Long memberId);

    public List<Member> findId(String email);

    public int countId(String email);

    public MailDTO createEmail(String email);

    String getTempPwd();

//    public void updatePwd(String str, String email);

//    public void profileImageUpdate(Long memberId, MemberModifyDTO memberModifyDTO, MultipartFile memberImageName);
}
