package com.youprice.onion.entity.member;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "member_id")
    private Member member; //팔로우한 회원번호 FK

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "follow_target_id")
    private Member target; //팔로우된 회원번호 FK

}
