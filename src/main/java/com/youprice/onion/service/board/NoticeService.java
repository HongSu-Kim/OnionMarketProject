package com.youprice.onion.service.board;

import com.youprice.onion.dto.board.NoticeDTO;
import com.youprice.onion.dto.board.NoticeFormDTO;
import com.youprice.onion.dto.board.NoticeUpdateDTO;
import com.youprice.onion.entity.board.NoticeImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface NoticeService {

    Long saveNotice(NoticeFormDTO noticeFormDTO, List<MultipartFile> noticeImageName) throws IOException;
    NoticeDTO findNoticeDTO(Long id);
    Page<NoticeDTO> searchNotice(String word, Pageable pageable);
    void update(Long noticeId, NoticeUpdateDTO noticeUpdateDTO) throws IOException;
    void delete(Long id);

    Page<NoticeDTO> findTypeNotice(Pageable pageable);
    List<NoticeDTO> findTypeQna();


    List<NoticeImage> storeImages(Long noticeId, List<MultipartFile> multipartFiles) throws IOException;
    String storePath(MultipartFile multipartFile) throws IOException;

    int updateView(Long id);

    void imageDelete(Long imageId);

}
