package com.youprice.onion.service.board.impl;

import com.youprice.onion.repository.board.NoticeRepository;
import com.youprice.onion.service.board.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

}
