package com.youprice.onion.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class NoticeImage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "image_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private NoticeBoard noticeBoard;

    @Column
    private String notice_image_name;

}
