package com.youprice.onion.service.member;

import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.dto.member.MemberJoinDTO;
import com.youprice.onion.dto.member.MemberModifyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Errors;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
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

    public MemberDTO findPwd(String email, String url) throws Exception;

    String modifyProfileImg(Long memberId, MultipartFile profileImg) throws IOException;

	List<MemberDTO> getChatMemberList(Long memberId);


	int chargeCash(Long memberId, int amount);

    Page<MemberDTO> getMemberList(Pageable pageable);

    void accountLock(Long targetId);

    void removeLock(Long targetId);
}
