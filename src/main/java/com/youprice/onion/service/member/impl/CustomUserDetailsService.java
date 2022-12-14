package com.youprice.onion.service.member.impl;

import com.youprice.onion.repository.order.WishRepository;
import com.youprice.onion.security.auth.CustomUserDetails;
import com.youprice.onion.dto.member.SessionDTO;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final WishRepository wishRepository;
    private final HttpSession session;

    @Override //userId가 DB에 있는지 확인
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Member member = memberRepository.findByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + userId));

        //탈퇴한 회원일 경우(role = ROLE_WITHDRAWAL)
        //계정 잠금당한 회원일 경우(role = ROLE_LOCK)
        if (member.getRole().name().equals("WITHDRAWAL")) {
            throw new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + userId);
        } else if (member.getRole().name().equals("LOCK")) {
            throw new BadCredentialsException("현재 일시적으로 이용이 제한된 계정입니다.");
        }

        SessionDTO sessionDTO = new SessionDTO(member);

        session.setAttribute("wishCount", wishRepository.countByMemberId(member.getId()));
        session.setAttribute("memberImageName", member.getMemberImageName());
        session.setAttribute("cash", member.getCash());

        //시큐리티 세션에 유저 정보 저장
        return new CustomUserDetails(sessionDTO);
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
