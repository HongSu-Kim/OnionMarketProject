package com.youprice.onion.entity.member;
import com.youprice.onion.entity.product.Category;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class MemberCategory {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_category_id")
    private Long id; //회원카테고리 번호 PK

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "member_id")
    private Member member;//회원번호 FK

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "category_id")
    private Category category; //카테고리번호 FK

}





















