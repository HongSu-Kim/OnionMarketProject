package com.youprice.onion.service.board;

import com.youprice.onion.dto.board.NoticeDTO;
import com.youprice.onion.entity.board.Notice;

public interface NoticeService {

    void saveNotice(NoticeDTO noticeDTO);

    NoticeDTO findNoticeDTO(Long id);

}
