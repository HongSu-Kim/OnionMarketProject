package com.youprice.onion.repository.product;


import com.youprice.onion.entity.member.Keyword;
import com.youprice.onion.entity.product.Town;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface TownRepositoy extends JpaRepository<Town,Long> {

    List<Town> findAll();

    List<Town> findAllByMemberId(Long memberId);


}





