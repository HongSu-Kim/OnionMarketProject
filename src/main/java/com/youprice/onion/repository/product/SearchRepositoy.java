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


        public List<Search> findSearch() {

            return em.createQuery("select  o from Search o where o.searchName = '유니폼'", Search.class)
                    .getResultList();

        }


@Transactional
        public int updatecount(String SearchName) {
            return em.createQuery("update Search as m " +
                            "set m.count = m.count+1 where m.searchName = :SearchName")
                    .setParameter("SearchName", SearchName)

                    .executeUpdate();
        }

        public Long Search() {

            return em.createQuery("select count(o) from Search o ", Long.class)
                    .getSingleResult();

        }

    }

}
