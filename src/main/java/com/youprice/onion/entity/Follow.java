package com.youprice.onion.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer follow_id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; //회원번호 FK
}
