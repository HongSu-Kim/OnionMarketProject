package com.youprice.onion.service.member;

import com.youprice.onion.dto.member.MemberDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface MemberService extends UserDetailsService {

    public Long saveMember(MemberDTO memberDTO);

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException;

    public MemberDTO getMemberDTO(Long memberId);

//    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException;
/*
    //회원가입
    Long memberJoin(MemberDTO.RequestMemberDTO dto);

    //회원정보 수정
    Long memberInfoModify(MemberDTO.RequestMemberDTO dto);

    public boolean checkUserIdDuplication(String userId);

    public boolean checkNicknameDuplication(String nickname);

    public boolean checkEmailDuplication(String email);
    */

}
