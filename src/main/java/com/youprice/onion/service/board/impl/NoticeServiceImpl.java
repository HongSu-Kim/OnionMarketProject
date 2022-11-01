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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
        Long noticeId = save.getId();
        List<NoticeImage> list = storeImages(noticeId, noticeImageName);
        for(NoticeImage noticeImage : list){
            noticeImageRepository.save(noticeImage);
        }
        return noticeId;
    }

    @Override
    public NoticeDTO findNoticeDTO(Long id) {
        return noticeRepository.findById(id).map(NoticeDTO::new).orElse(null);
    }

    public Page<NoticeDTO> searchNotice( String word, Pageable pageable){
        return noticeRepository.findAllByNoticeSubjectContainingAndNoticeTypeLike(word, NoticeType.NOTICE, pageable).map(NoticeDTO::new);
    }

    @Transactional
    public void update(Long noticeId, NoticeUpdateDTO noticeUpdateDTO) throws IOException {
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new IllegalArgumentException("수정이 불가합니다"));
        Long id = notice.getId();
        Notice update = notice.updateNotice(id, noticeUpdateDTO);

        List<NoticeImage> list = storeImages(noticeId, noticeUpdateDTO.getNoticeImageName());
        for(NoticeImage noticeImage : list){
            noticeImageRepository.save(noticeImage);
        }
        noticeRepository.save(update);
    }

    @Transactional
    public void delete(Long noticeId) {
        noticeRepository.deleteById(noticeId);
    }

    public Page<NoticeDTO> findTypeNotice(Pageable pageable){
        return noticeRepository.findAllByNoticeTypeLikeOrderById(NoticeType.NOTICE, pageable).map(NoticeDTO::new);
    }
    public List<NoticeDTO> findTypeQna(){
    return noticeRepository.findAllByNoticeTypeLikeOrderByIdDesc(NoticeType.QNA)
            .stream().map(NoticeDTO::new).collect(Collectors.toList());
    }

    public String storePath(MultipartFile multipartFile) throws IOException{
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\notice";

        if(multipartFile.isEmpty()){
            return null;
        }

        String noticeImageName = multipartFile.getOriginalFilename();

        String ext = noticeImageName.substring(noticeImageName.lastIndexOf(".")+1);
        String uuid = UUID.randomUUID().toString();
        noticeImageName = uuid + "." + ext;
        multipartFile.transferTo(new File(filePath, noticeImageName));

        return noticeImageName;
    }

    public List<NoticeImage> storeImages(Long id, List<MultipartFile> multipartFiles) throws IOException{

        List<NoticeImage> storeImageList = new ArrayList<>();
        Notice notice = noticeRepository.findById(id).orElse(null);
        for (MultipartFile multipartFile : multipartFiles) {

            if(!multipartFile.isEmpty()){
                String noticeImageName = storePath(multipartFile); // uuid 반환
                NoticeImage noticeImage = new NoticeImage(notice, noticeImageName);
                storeImageList.add(noticeImage);
            }
        }
        return storeImageList;
    }
    @Transactional
    public int updateView(Long id){
        return noticeRepository.updateView(id);
    } //조회수 상승

    @Transactional
    public void imageDelete(Long imageId){
        NoticeImage noticeImage = noticeImageRepository.findById(imageId).orElse(null);
        noticeImageRepository.delete(noticeImage);
    }
}
