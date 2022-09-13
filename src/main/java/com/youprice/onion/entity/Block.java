package com.youprice.onion.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer blockNum;

    @JoinColumn
    private Integer member_id;
}
