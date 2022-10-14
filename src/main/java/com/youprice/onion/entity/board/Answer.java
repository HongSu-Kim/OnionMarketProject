package com.youprice.onion.entity.board;

import com.youprice.onion.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "answer_id")
    private Long id; // 답변번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry; // 문의글번호 FK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 회원번호 FK

    private String answerContent; // 답변내용
    private LocalDateTime answerDate; // 답변등록일

    public Answer(Inquiry inquiry, Member member, String answerContent) {
        this.inquiry = inquiry;
        this.member = member;
        this.answerContent = answerContent;
        this.answerDate = LocalDateTime.now();
    }

    public void updateAnswer(Long id, String answerContent){
        this.id = id;
        this.answerContent = answerContent;
        this.answerDate = LocalDateTime.now();
    }
}
