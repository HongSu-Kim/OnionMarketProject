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
    private Member member; //회원번호 FK

}
