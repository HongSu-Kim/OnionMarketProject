package com.youprice.onion.dto.member;

import com.youprice.onion.entity.member.Block;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlockDTO {

    private Long blockId;
    private MemberDTO memberDTO;
    private MemberDTO targetDTO;

    public BlockDTO(Block block) {
        blockId = block.getId();
        memberDTO = new MemberDTO(block.getMember());
        targetDTO = new MemberDTO(block.getTarget());

        memberDTO = new MemberDTO(block.getMember());
    }

}
