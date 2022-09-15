package com.youprice.onion.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import javax.persistence.*;


@Entity
@Getter
public class Tag {

        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
        @Column(name = "tag_id")
        private Long id; //태그번호 PK

        private String tagName; //태그 명

        private int tagCount; //태그 조회수

        @OneToMany(mappedBy = "tag") //상품태그번호
        private List<ProductTag> productTagList = new ArrayList<>();








}
