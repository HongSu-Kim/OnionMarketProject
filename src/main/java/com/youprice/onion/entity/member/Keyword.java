package com.youprice.onion.entity.member;
import com.youprice.onion.dto.member.KeywordCreateDTO;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Keyword {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "keyword_id")
    private Long id; //키워드 번호 PK

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "member_id")
    private Member member;//회원번호 FK

    private String keywordName;//키워드명

    @OneToMany(mappedBy = "keyword")//이미지번호
    private List<KeywordAlarm> keywordAlarmList = new ArrayList<>();

    public Keyword keywordCreate(KeywordCreateDTO keywordCreateDto, Member member){

  //  this.id = keywordCreateDto.getId();
    this.member = member;
    this.keywordName = keywordCreateDto.getKeywordName();

    return this;
    }


}

