package com.youprice.onion.entity.member;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class KeywordAlarm {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "keyword_alarm_id")
    private Long id; //키워드알림 번호 PK

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "member_id")
    private Member member;//회원번호 FK

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;//키워드번호 FK



    public KeywordAlarm keywordCreate( Member member,Keyword keyword){


    this.member = member;
    this.keyword = keyword;

    return this;
    }


}

