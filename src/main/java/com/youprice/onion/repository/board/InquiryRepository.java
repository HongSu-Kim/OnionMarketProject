package com.youprice.onion.repository.board;

import com.youprice.onion.entity.board.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
//    @Query(value = "select i from Inquiry i where i.member.name li",nativeQuery = true)
//    Page<Inquiry> findByMemberName(String username, Pageable pageable);
    Page<Inquiry> findInquiriesByMember_NameContainingOrderById(String username, Pageable pageable);
    Page<Inquiry> findInquiriesByInquiryTypeLikeAndInquirySubjectContainingOrderById(String type,String subject,Pageable pageable);
}
