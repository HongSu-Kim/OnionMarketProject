package com.youprice.onion.dto.board;

import com.youprice.onion.entity.board.NoticeImage;
import lombok.Getter;

@Getter
public class NoticeImageDTO {

    private Long id;

    private Long noticeId;

    private String noticeImageName;

    public NoticeImageDTO(NoticeImage noticeImage) {
        this.id = noticeImage.getId();
        this.noticeId = noticeImage.getNotice().getId();
        this.noticeImageName = noticeImage.getNoticeImageName();
    }
}
