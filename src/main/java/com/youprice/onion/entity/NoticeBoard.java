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
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public enum NoticeType {
        notice, qna, event
    }

    @Column
    public NoticeType notice_type;

    @Column
    public String notice_subject;

    @Column
    public String notice_content;

    @Column
    private LocalDateTime notice_date;

    @Column
    public int hit_count;


}
