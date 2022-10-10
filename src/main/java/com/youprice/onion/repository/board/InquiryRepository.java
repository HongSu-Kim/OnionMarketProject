package com.youprice.onion.repository.board;

import com.youprice.onion.entity.board.Inquiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    Page<Inquiry> findInquiriesByMember_NameContainingOrderById(String username, Pageable pageable);
}
