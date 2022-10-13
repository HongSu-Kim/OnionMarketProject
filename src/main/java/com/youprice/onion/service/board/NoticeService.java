package com.youprice.onion.service.board;

import com.youprice.onion.dto.board.NoticeDTO;
import com.youprice.onion.dto.board.NoticeImageDTO;
import com.youprice.onion.entity.board.Notice;
import com.youprice.onion.entity.board.NoticeImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface NoticeService {

    Long saveNotice(NoticeDTO noticeDTO, List<MultipartFile> noticeImageName) throws IOException;

    NoticeDTO findNoticeDTO(Long id);

    void update(Long id, NoticeDTO noticeDTO);

    void delete(Long id);

    Page<NoticeDTO> findAllNotice(Pageable pageable);

    //사진
    String filePath();
    List<NoticeImage> storeImages(Long noticeId, List<MultipartFile> multipartFiles) throws IOException;
    String storePath(MultipartFile multipartFile) throws IOException;

}
