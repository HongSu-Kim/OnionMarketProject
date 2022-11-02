package com.youprice.onion.repository.board;

import com.youprice.onion.entity.board.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    Page<Inquiry> findAllByMember_NicknameContaining(String username, Pageable pageable);
    Page<Inquiry> findAllByInquirySubjectContaining(String subject, Pageable pageable);
    Page<Inquiry> findAllByInquiryTypeContainingAndInquirySubjectContaining(String type, String subject, Pageable pageable);


    Page<Inquiry> findAllByMember_Id(Long memberId, Pageable pageable);
    @Query(value = "select i from Inquiry i where i.inquiryDate between :dt_fr and :dt_to and i.member.id = :memberId", nativeQuery = true)
    Page<Inquiry> findPeriod(LocalDate dt_fr, LocalDate dt_to, Long memberId, Pageable pageable);

    @Query(value = "select i from Inquiry i where i.inquiryDate between :dt_fr and :dt_to", nativeQuery = true)
    Page<Inquiry> findAllByPeriod(LocalDate dt_fr, LocalDate dt_to, Pageable pageable);

    //기간 검색 - 전체 영역
    @Query(value = "select i from Inquiry i where i.inquiryDate between :dt_fr and :dt_to and i.member.nickname like :username", nativeQuery = true)
    Page<Inquiry> searchNamePeriod(LocalDate dt_fr, LocalDate dt_to, String username, Pageable pageable);

    @Query(value = "select i from Inquiry i where i.inquiryDate between :dt_fr and :dt_to and i.inquirySubject like CONCAT('%',:subject,'%')", nativeQuery = true)
    Page<Inquiry> searchAllByPeriod(LocalDate dt_fr, LocalDate dt_to, @Param("subject") String subject, Pageable pageable);

    @Query(value = "select i from Inquiry i where i.inquiryDate between :dt_fr and :dt_to and i.inquiryType like :type and i.inquirySubject like '%:subject%'", nativeQuery = true)
    Page<Inquiry> searchTypePeriod(LocalDate dt_fr, LocalDate dt_to, String type, String subject, Pageable pageable);

}
