package com.youprice.onion.dto.member;

import com.youprice.onion.entity.member.Follow;
import com.youprice.onion.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FollowListDTO {

    private Long followId;
    private Long targetId;
    private String memberNickname;
    private String memberImageName;
    private Double userGrade;
    private int complaintCount;
    private int followListSize;

    public FollowListDTO(Follow follow, int followListSize) {
        followId = follow.getId();
        Member target = follow.getTarget();
        memberNickname = target.getNickname();
        memberImageName = target.getMemberImageName();
        userGrade = target.getUserGrade();
        complaintCount = target.getComplaintCount();
        this.followListSize = followListSize;
    }
}
