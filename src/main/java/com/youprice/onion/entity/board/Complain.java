package com.youprice.onion.entity.board;

import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@DynamicInsert
public class Complain {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "complain_id")
    private Long id; // 신고번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 신고자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; // 상품번호 FK

    private String complainType; // 신고유형
    private LocalDate complainDate; //신고일자
    private String complainContent; // 신고내용
    private String status; // 처리상태

    public Complain(Member member, Product product, String complainType, String complainContent) {
        this.member = member;
        this.product = product;
        this.complainType = complainType;
        this.complainDate = LocalDate.now();
        this.complainContent = complainContent;
    }
    public void updateStatus(String status){
        this.status = status;
    }
}
