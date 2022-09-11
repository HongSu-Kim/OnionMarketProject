package com.youprice.onion.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer memberId;

}
