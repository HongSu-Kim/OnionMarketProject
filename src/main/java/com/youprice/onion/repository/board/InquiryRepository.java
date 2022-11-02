package com.youprice.onion.repository.board;

import com.youprice.onion.entity.board.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    Page<Inquiry> findAllByMember_NicknameContaining(String username, Pageable pageable);
    Page<Inquiry> findAllByInquiryTypeContainingAndInquirySubjectContaining(String type, String subject, Pageable pageable);
    Page<Inquiry> findAllByInquirySubjectContaining(String subject, Pageable pageable);

    Page<Inquiry> findAllByMember_Id(Long memberId, Pageable pageable);
    @Query(value = "select i from Inquiry i where i.inquiryDate between :dt_fr and :dt_to and i.member.id = :memberId")
    Page<Inquiry> findPeriod(LocalDate dt_fr, LocalDate dt_to, Long memberId, Pageable pageable);

}
