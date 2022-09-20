package com.youprice.onion.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "inquiry_id")
    private Integer id; // 문의번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 회원번호 FK

    private String inquiryType; // 문의유형
    private String inquirySubject; // 문의제목
    private String inquiryContent; // 문의내용
    private LocalDateTime inquiryDate; // 문의등록일

    @OneToOne(mappedBy = "inquiry")
    private Answer answer;


}
