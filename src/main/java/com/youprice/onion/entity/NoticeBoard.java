package com.youprice.onion.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class NoticeBoard{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "notice_board_id")
    private Integer id; //공지번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //회원번호 FK

    public enum NoticeType {
        NOTICE, QNA, EVENT
    }

    @Column
    public NoticeType notice_type; //공지 타입(notice, qna, event ...)

    @Column
    public String notice_subject; //공지제목

    @Column
    public String notice_content; //공지내용

    @Column
    private LocalDateTime notice_date; //작성일자

    @Column
    public int hit_count; //조회수


}
