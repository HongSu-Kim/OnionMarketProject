package com.youprice.onion.dto.member;

import com.youprice.onion.entity.member.Block;
import com.youprice.onion.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlockListDTO {

    private Long blockId;
    private Long targetId;
    private String memberNickname;
    private String memberImageName;
    private Double userGrade;
    private int complaintCount;
    private int blockListSize;

    public BlockListDTO(Block block, int blockListSize) {
        blockId = block.getId();
        targetId = block.getTarget().getId();
        Member target = block.getTarget();
        memberNickname = target.getNickname();
        userGrade = target.getUserGrade();
        complaintCount = target.getComplaintCount();
        this.blockListSize = blockListSize;
    }
}
