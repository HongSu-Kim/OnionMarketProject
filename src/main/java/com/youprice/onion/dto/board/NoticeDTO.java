package com.youprice.onion.dto.board;

import com.youprice.onion.entity.board.Notice;
import com.youprice.onion.entity.board.NoticeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Not;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
public class NoticeDTO {

    private Long noticeId;

    private Long memberId;

    private String noticeType; //공지타입(notice, qna, event ...)

    private String noticeSubject; //공지제목

    private String noticeContent; //공지내용

    private LocalDate noticeDate; //작성일자

    private List<NoticeImageDTO> noticeImageList;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int hitCount; //조회수

    public NoticeDTO(Notice notice) {
        this.noticeId = notice.getId();
        this.memberId = notice.getMember().getId();
        this.noticeType = notice.getNoticeType().name();
        this.noticeSubject = notice.getNoticeSubject();
        this.noticeContent = notice.getNoticeContent();
        this.noticeDate = notice.getNoticeDate();
        this.hitCount = notice.getHitCount();
        this.noticeImageList = notice.getNoticeImageList().stream().map(NoticeImageDTO::new).collect(Collectors.toList());
    }
}
