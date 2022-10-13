package com.youprice.onion.service.member.impl;

import com.youprice.onion.dto.member.CustomUserDetails;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final HttpSession session;

    @Override //userId가 DB에 있는지 확인
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + userId));

        session.setAttribute("member", new MemberDTO.Response(member));

        //시큐리티 세션에 유저 정보 저장
        return new CustomUserDetails(member);
    }

/*    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Member> memberWrapper = memberRepository.findByUserId(userId);
        Member member = memberWrapper.orElse(null);

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("admin").equals(userId)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getValue()));
        }
//        authorities.add(new SimpleGrantedAuthority(member.getRole().name()));

        return new User(member.getUserId(), member.getPwd(), authorities);
    }*/
}
