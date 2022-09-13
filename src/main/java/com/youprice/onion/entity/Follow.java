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
    private Integer followNum;

    @JoinColumn
    private Integer member_id;

}
