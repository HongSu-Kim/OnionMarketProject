package com.youprice.onion.service.board.impl;

import com.youprice.onion.dto.board.NoticeDTO;
import com.youprice.onion.dto.board.NoticeFormDTO;
import com.youprice.onion.dto.board.NoticeUpdateDTO;
import com.youprice.onion.entity.board.Notice;
import com.youprice.onion.entity.board.NoticeImage;
import com.youprice.onion.entity.board.NoticeType;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.repository.board.NoticeImageRepository;
import com.youprice.onion.repository.board.NoticeRepository;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.service.board.NoticeService;
import com.youprice.onion.util.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;
    private final NoticeImageRepository noticeImageRepository;

    @Transactional
    public Long saveNotice(NoticeFormDTO form, List<MultipartFile> noticeImageName) throws IOException {

        Member member = memberRepository.findById(form.getMemberId()).orElse(null);
        Notice notice = new Notice(member, form.getNoticeType(), form.getNoticeSubject(), form.getNoticeContent());
        Notice save = noticeRepository.save(notice);

        List<String> storeName = ImageUtil.store(noticeImageName, "notice");
        for (String storeImageName : storeName) {
            NoticeImage noticeImage = new NoticeImage(save, storeImageName);
            noticeImageRepository.save(noticeImage);
        }
        return save.getId();
    }

    @Override
    public NoticeDTO findNoticeDTO(Long id) {
        return noticeRepository.findById(id).map(NoticeDTO::new).orElse(null);
    }

    // 제목 검색
    public Page<NoticeDTO> searchNotice( String word, Pageable pageable){
        return noticeRepository.findAllByNoticeSubjectContainingAndNoticeTypeLike(word, NoticeType.NOTICE, pageable).map(NoticeDTO::new);
    }

    // 수정 (새로운 이미지 추가)
    @Transactional
    public void update(Long noticeId, NoticeUpdateDTO noticeUpdateDTO) throws IOException {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new IllegalArgumentException("수정이 불가합니다"));
        notice.updateNotice(notice.getId(), noticeUpdateDTO);

        if(noticeUpdateDTO.getNoticeImageName() != null) {
            List<String> storeName = ImageUtil.store(noticeUpdateDTO.getNoticeImageName(), "notice");
            for (String storeImageName : storeName) {
                NoticeImage noticeImage = new NoticeImage(notice, storeImageName);
                noticeImageRepository.save(noticeImage);
            }
        }
        noticeRepository.save(notice);
    }

    @Transactional
    public void delete(Long noticeId) {
        noticeRepository.deleteById(noticeId);
    }

    // 공지사항 list
    public Page<NoticeDTO> findTypeNotice(Pageable pageable){
        return noticeRepository.findAllByNoticeTypeLikeOrderByIdDesc(NoticeType.NOTICE, pageable).map(NoticeDTO::new);
    }

    // qna 리스트
    public List<NoticeDTO> findTypeQna(){
    return noticeRepository.findAllByNoticeTypeLikeOrderByIdDesc(NoticeType.QNA)
            .stream().map(NoticeDTO::new).collect(Collectors.toList());
    }

    //조회수 상승
    @Transactional
    public int updateView(Long id){
        return noticeRepository.updateView(id);
    }

    // 이미지 개별삭제
    @Transactional
    public void imageDelete(Long imageId){
        NoticeImage noticeImage = noticeImageRepository.findById(imageId).orElse(null);
        noticeImageRepository.delete(noticeImage);
    }
}
