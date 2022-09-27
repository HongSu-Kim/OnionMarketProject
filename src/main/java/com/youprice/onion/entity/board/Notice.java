package com.youprice.onion.entity.board;

import com.youprice.onion.entity.member.Member;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Notice{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "notice_id")
    private Long id; //공지번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //회원번호 FK

    private enum NoticeType {
        NOTICE, QNA, EVENT
    }

    @Enumerated(EnumType.STRING)
    private NoticeType notice_type; //공지 타입(notice, qna, event ...)

    private String notice_subject; //공지제목
    private String notice_content; //공지내용
    private LocalDateTime notice_date; //작성일자
    private int hit_count; //조회수


    @OneToMany(mappedBy = "notice")
    private List<NoticeImage> noticeImageList = new ArrayList<>();

}
