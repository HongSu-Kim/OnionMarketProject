package com.youprice.onion.service.member;

import com.youprice.onion.dto.member.MemberDTO;

public interface MemberService {

    public Long saveMember(MemberDTO.Request dto);

    public MemberDTO getMemberDTO(Long memberId);

}
