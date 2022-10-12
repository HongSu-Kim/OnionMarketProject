package com.youprice.onion.service.board.impl;

import com.youprice.onion.dto.board.NoticeDTO;
import com.youprice.onion.dto.member.MemberDTO;
import com.youprice.onion.entity.board.Notice;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.repository.board.NoticeRepository;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.service.board.NoticeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    public void saveNotice(NoticeDTO noticeDTO){
        Member member = memberRepository.findById(noticeDTO.getMemberId()).orElse(null);
        Notice notice = new Notice(member, noticeDTO.getNoticeSubject(), noticeDTO.getNoticeContent());
        noticeRepository.save(notice);
    }

    @Override
    public NoticeDTO findNoticeDTO(Long id) {
        return noticeRepository.findById(id).map(NoticeDTO::new).orElse(null);
    }

    //수정



    //삭제


}
