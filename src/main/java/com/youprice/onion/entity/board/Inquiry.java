package com.youprice.onion.entity.board;

import com.youprice.onion.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@DynamicInsert
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "inquiry_id")
    private Long id; // 문의번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 회원번호 FK

    private String inquiryType; // 문의유형
    private String detailType; // 상세유형

    private String inquirySubject; // 문의제목
    private String inquiryContent; // 문의내용
    private LocalDate inquiryDate; // 문의등록일
    @ColumnDefault("'답변대기'")
    private String status; // 답변상태
    private boolean secret; // 비밀글 여부

    @OneToOne(mappedBy = "inquiry")
    private Answer answer;

    public Inquiry(Member member, String inquiryType, String detailType, String inquirySubject,
                   String inquiryContent, String status, boolean secret) {
        this.member = member;
        this.inquiryType = inquiryType;
        this.detailType = detailType;
        this.inquirySubject = inquirySubject;
        this.inquiryContent = inquiryContent;
        this.inquiryDate = LocalDate.now();
        this.status = status;
        this.secret = secret;
    }
}
