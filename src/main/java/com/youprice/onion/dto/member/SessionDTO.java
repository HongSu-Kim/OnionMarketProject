package com.youprice.onion.dto.member;

import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.member.Role;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionDTO implements Serializable { //인증된 사용자 정보를 세션에 저장하기 위한 클래스
    //엔티티 클래스에 직렬화를 한다면 추후에 다른 엔티티와 연관관계를 맺을 시,
    //직렬화 대상에 다른 엔티티까지 포함될 수 있어 성능 이슈, 부수 효과 우려가 있기 때문에 세션 저장용 DTO 클래스 생성

    private Long id;
    private Role role;
    private String userId;
    private String pwd;
    private String nickname;
    private String memberImageName;

    //Entity -> DTO
    public SessionDTO(Member member) {
        this.id = member.getId();
        this.role = member.getRole();
        this.userId = member.getUserId();
        this.pwd = member.getPwd();
        this.nickname = member.getNickname();
        this.memberImageName = member.getMemberImageName();
    }
}