package com.youprice.onion.entity.member;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "block_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "member_id")
    private Member member; //차단한 회원번호 FK

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "block_target_id")
    private Member target; //차단 당한 회원번호 FK

    @Builder
    public Block(Member member, Member target) {
        this.member = member;
        this.target = target;
    }

    public Block() {

    }
}