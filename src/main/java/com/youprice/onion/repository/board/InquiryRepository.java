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


    // 1명
    Page<Inquiry> findAllByMember_Id(Long memberId, Pageable pageable);
    @Query(value = "select i from Inquiry i where i.inquiryDate between :dt_fr and :dt_to and i.member.id = :memberId")
    Page<Inquiry> findPeriod(@Param("dt_fr") LocalDate dt_fr, @Param("dt_to") LocalDate dt_to, @Param("memberId") Long memberId, Pageable pageable);
    @Query(value = "select i from Inquiry i where i.inquiryDate between :dt_fr and :dt_to and i.status = :status and i.member.id = :memberId")
    Page<Inquiry> findByPeriodandStatus(@Param("dt_fr") LocalDate dt_fr, @Param("dt_to") LocalDate dt_to, @Param("status") String status, @Param("memberId") Long memberId, Pageable pageable);

    @Query(value = "select i from Inquiry i where i.status = :status and i.member.id = :memberId")
    Page<Inquiry> findByStatus(@Param("status") String status, @Param("memberId") Long memberId, Pageable pageable);


    // 전체
    @Query(value = "select i from Inquiry i where i.inquiryDate between :dt_fr and :dt_to")
    Page<Inquiry> findAllByPeriod(@Param("dt_fr") LocalDate dt_fr, @Param("dt_to") LocalDate dt_to, Pageable pageable);

    //기간 검색 - 전체 영역
    @Query(value = "select i from Inquiry i where i.inquiryDate between :dt_fr and :dt_to and i.member.nickname like :username")
    Page<Inquiry> searchNamePeriod(@Param("dt_fr") LocalDate dt_fr, @Param("dt_to") LocalDate dt_to, @Param("username") String username, Pageable pageable);

    @Query(value = "select i from Inquiry i where i.inquiryDate between :dt_fr and :dt_to and i.inquirySubject like CONCAT('%',:subject,'%')")
    Page<Inquiry> searchAllByPeriod(@Param("dt_fr") LocalDate dt_fr, @Param("dt_to") LocalDate dt_to, @Param("subject") String subject, Pageable pageable);

    @Query(value = "select i from Inquiry i where i.inquiryDate between :dt_fr and :dt_to and i.inquiryType like :type and i.inquirySubject like CONCAT('%',:subject,'%')")
    Page<Inquiry> searchTypePeriod(@Param("dt_fr") LocalDate dt_fr, @Param("dt_to") LocalDate dt_to, @Param("type") String type, @Param("subject") String subject, Pageable pageable);

}
