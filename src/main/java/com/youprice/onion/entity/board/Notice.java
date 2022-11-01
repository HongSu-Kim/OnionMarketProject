package com.youprice.onion.entity.board;

import com.youprice.onion.dto.board.NoticeUpdateDTO;
import com.youprice.onion.entity.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Notice{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "notice_id")
    private Long id; //공지번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //회원번호 FK

    @Setter
    @Enumerated(EnumType.STRING)
    private NoticeType noticeType; //공지타입(notice, qna, event ...)

    private String noticeSubject; //공지제목
    private String noticeContent; //공지내용
    private LocalDate noticeDate; //작성일자
    private int hitCount; //조회수


    @OneToMany(mappedBy = "notice", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<NoticeImage> noticeImageList;

    public Notice(Member member, String noticeType, String noticeSubject, String noticeContent) {
        this.member = member;
        for(NoticeType nt : NoticeType.class.getEnumConstants()){
            if(nt.name().equals(noticeType)){
                this.noticeType = nt;
            }
        }
        this.noticeSubject = noticeSubject;
        this.noticeContent = noticeContent;
        this.noticeDate = LocalDate.now();
        this.hitCount = 0;
    }

    public Notice updateNotice(Long id, NoticeUpdateDTO noticeUpdateDTO){
        this.id = id;
        for(NoticeType nt : NoticeType.class.getEnumConstants()){
            if(nt.name().equals(noticeUpdateDTO.getNoticeType())){
                this.noticeType = nt;
            }
        }
        this.noticeSubject = noticeUpdateDTO.getNoticeSubject();
        this.noticeContent = noticeUpdateDTO.getNoticeContent();
        return this;
    }

}
