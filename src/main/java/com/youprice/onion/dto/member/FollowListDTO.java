package com.youprice.onion.dto.member;

import com.youprice.onion.entity.member.Follow;
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
    private int userGrade;
    private int complaintCount;
    private int followListSize;

    public FollowListDTO(Follow follow, int followListSize) {
        followId = follow.getId();
        Follow target = follow.getTarget();
        memberNickname = target.getMember().getNickname();
        memberImageName = target.getMember().getMemberImageName();
        userGrade = target.getMember().getUserGrade();
        complaintCount = target.getMember().getComplaintCount();
        this.followListSize = followListSize;
    }
}
