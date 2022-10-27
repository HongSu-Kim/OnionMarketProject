package com.youprice.onion.entity.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
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
    private Follow target; //팔로우된 회원번호 FK

    @Builder
    public Follow(Member member, Follow target) {
        this.member = member;
        this.target = target;
    }

}
