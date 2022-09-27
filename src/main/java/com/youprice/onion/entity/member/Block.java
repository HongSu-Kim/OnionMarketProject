package com.youprice.onion.entity.member;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "block_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "member_id")
    private Member member; //회원-회원번호 FK

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "block_id_num")
    private Member target; //회원-회원번호 FK

}
