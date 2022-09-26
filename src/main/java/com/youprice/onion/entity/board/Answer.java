package com.youprice.onion.entity.board;

import com.youprice.onion.entity.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "answer_id")
    private Long id; // 답변번호 PK

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry; // 문의글번호 FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 회원번호 FK

    private String answerSubject; // 답변제목
    private String answerContent; // 답변내용
    private LocalDateTime answerDate; // 답변등록일

}
