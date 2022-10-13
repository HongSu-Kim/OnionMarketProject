package com.youprice.onion.entity.board;

import com.youprice.onion.entity.board.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class NoticeImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "notice_image_id")
    private Long id; //공지 이미지번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Notice notice; //공지 번호 FK

    private String noticeImageName; //첨부파일명

    public NoticeImage(Notice notice, String noticeImageName) {
        this.notice = notice;
        this.noticeImageName = noticeImageName;
    }
}
