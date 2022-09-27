package com.youprice.onion.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
public class Notice{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "notice_id")
    private Integer id; //공지번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //회원번호 FK

    private enum NoticeType {
        NOTICE, QNA, EVENT
    }

    @Column
    private NoticeType noticeType; //공지타입(notice, qna, event ...)

    @Column
    private String noticeSubject; //공지제목

    @Column
    private String noticeContent; //공지내용

    @Column
    private LocalDateTime noticeDate; //작성일자

    @Column
    private int hitCount; //조회수

    @OneToMany(mappedBy = "notice")
    private List<NoticeImage> noticeImageList;


}
