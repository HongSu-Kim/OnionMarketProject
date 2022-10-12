package com.youprice.onion.service.board;

import com.youprice.onion.dto.board.NoticeDTO;
import com.youprice.onion.entity.board.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeService {

    void saveNotice(NoticeDTO noticeDTO);

    NoticeDTO findNoticeDTO(Long id);

    void update(Long id, NoticeDTO noticeDTO);

    void delete(NoticeDTO noticeDTO);

    Page<NoticeDTO> findAllNotice(Pageable pageable);



}
