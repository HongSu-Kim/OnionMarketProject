package com.youprice.onion.repository.member;
import com.youprice.onion.dto.member.ProhibitionKeywordFindDTO;
import com.youprice.onion.entity.member.ProhibitionKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository

public interface ProhibitionKeywordRepositoy extends JpaRepository<ProhibitionKeyword,Long> {

 Optional<ProhibitionKeyword> findByProhibitionKeywordName(String ProhibitionKeywordName);

 ProhibitionKeyword findAllByProhibitionKeywordName(String ProhibitionKeywordName);

 boolean existsByProhibitionKeywordNameAndProhibitionKeywordNameContaining(String ProhibitionKeywordName,String ProhibitionKeywordName1);

 Long deleteByProhibitionKeywordName(String ProhibitionKeywordName);


}
