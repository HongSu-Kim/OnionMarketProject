package com.youprice.onion.entity.board;

import com.youprice.onion.entity.board.Notice;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class NoticeImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "notice_image_id")
    private Integer id; //공지 이미지번호 PK

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Notice notice; //공지 번호 FK

    @Column
    private String noticeImageName; //첨부파일명

}
