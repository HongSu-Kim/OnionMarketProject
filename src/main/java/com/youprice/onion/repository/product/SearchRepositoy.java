package com.youprice.onion.repository.product;

import com.youprice.onion.entity.product.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;


public interface SearchRepositoy extends JpaRepository<Search,Long> {


   public Search findBySearchName(String searchName);



    @Repository
    @RequiredArgsConstructor


    public class Searchrepositoy {


        private final EntityManager em;



@Transactional
        public int updatecount(String SearchName) {  //검색명 입력시 검색수 1씩증가
            return em.createQuery("update Search as m " +
                            "set m.searchCount = m.searchCount+1 where m.searchName = :SearchName")
                    .setParameter("SearchName", SearchName)

                    .executeUpdate();
        }

    }

}
