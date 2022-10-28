package com.youprice.onion.dto.member;

import com.youprice.onion.entity.member.Follow;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FollowDTO {

    private Long followId;
    private MemberDTO memberDTO;
    private MemberDTO targetDTO;

    public FollowDTO(Follow follow) {
        followId = follow.getId();
        memberDTO = new MemberDTO(follow.getMember());
        targetDTO = new MemberDTO(follow.getTarget());
    }

}
