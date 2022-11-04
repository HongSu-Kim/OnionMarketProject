package com.youprice.onion.service.board.impl;

import com.youprice.onion.dto.board.ComplainDTO;
import com.youprice.onion.dto.board.ComplainFormDTO;
import com.youprice.onion.entity.board.Complain;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.entity.product.ProductProgress;
import com.youprice.onion.repository.board.ComplainRepository;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.service.board.ComplainService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComplainServiceImpl implements ComplainService {

    private final ComplainRepository complainRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ComplainDTO saveComplain(ComplainFormDTO form){
        Member member = memberRepository.findById(form.getMemberId()).orElse(null);
        Product product = productRepository.findById(form.getProductId()).orElse(null);

        Complain complain = new Complain(member,product,form.getComplainType(), form.getComplainContent());
        Complain save = complainRepository.save(complain);
        return complainRepository.findById(save.getId()).map(ComplainDTO::new).orElse(null);
    }

    // 중복검사 (회원은 특정 상품당 1번만 신고)
    public List<ComplainDTO> checkComplain(Long memberId){
        return complainRepository.findAllByMemberId(memberId)
                .stream().map(ComplainDTO::new).collect(Collectors.toList());
    }


    @Transactional
    public String modifyStatus(Long complainId, String select) {
        Complain complain = complainRepository.findById(complainId).orElse(null);
        Member member = complain.getProduct().getMember();

        if(select.equals("complete")) {
            complain.updateStatus(select);
            complainRepository.save(complain);
            member.addComplainCount(); // 처리완료가 되면 신고대상 회원의 complainCount 증가
            memberRepository.save(member);

            if (member.getComplaintCount() >= 1) {
                Product product = productRepository.findById(complain.getProduct().getId()).orElse(null);
                product.progressUpdate(ProductProgress.BLIND);
                product.blindImage(null);
                productRepository.save(product);
            }
            return select;

        } else if (select.equals("cancel")) { // 접수취소되면 삭제
            complain.updateStatus(select);
            complainRepository.delete(complain);
            return select;

        } else if (select.equals("clear")) { // 처리된 신고를 다시 취소
            complain.updateStatus(select);
            complainRepository.save(complain);
            member.ComplainCancel();
            memberRepository.save(member);

            if (member.getComplaintCount() == 0) {
                Product product = productRepository.findById(complain.getProduct().getId()).orElse(null);
                product.progressUpdate(ProductProgress.SALESON);

                String productImageName = product.getProductImageList().get(0).getProductImageName();
                product.blindImage(productImageName);
                productRepository.save(product);
            }
            return select;
        }
        return select;
    }

    @Override
    public Page<ComplainDTO> findAll(Pageable pageable) {
        return complainRepository.findAll(pageable).map(ComplainDTO::new);
    }
}
