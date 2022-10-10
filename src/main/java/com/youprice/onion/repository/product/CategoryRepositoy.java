package com.youprice.onion.repository.product;


import com.youprice.onion.dto.product.CategoryUpdateDTO;
import com.youprice.onion.entity.product.Category;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface CategoryRepositoy extends JpaRepository<Category, Long> {

    Optional<Category> findByCategoryName(String topcategoryName);
    Optional<Category> findByCategoryNameAndCategory(String topcategoryName,Category category);

    List<Category> findAll();

    Category deleteById(CategoryUpdateDTO id);


    @Repository
    @RequiredArgsConstructor


    public class Categoryrepositoy {


        private final EntityManager em;

        public List<Category> findCategory() {


            return em.createQuery("select o from Category o where o.category is null ", Category.class)
                    .getResultList();

        }

        public List<Category> findSubcategory() { //하위카테고리조회


            return em.createQuery("select o from Category o  where o.category is not null ", Category.class)
                    .getResultList();

        }


        public Category findtopcategoryName(String topcategoryName){
            return em.createQuery("select o from Category o where o.categoryName= : topcategoryName", Category.class)
                    .setParameter("topcategoryName", topcategoryName)
                    .getSingleResult();
        }

        @Transactional

        public void deleteCategory(Long id){

           em.createQuery("delete from Category o where o.id=:id", Category.class)
                    .setParameter("id", id);
                   return;

        }





        public List<Category> finduniform() {


            return em.createQuery("select o from Category o  where o.category='22'", Category.class)
                    .getResultList();

        }



        public List<Category> findfootballboot() {


            //select m from Member m join fetch m.team
            return em.createQuery("select o from Category o  where o.category='22' ", Category.class)
                    .getResultList();

        }

        public List<Category> uniformPARENT_ID() {


            return em.createQuery("select o from Category o where o.category = '22' ", Category.class)
                    .getResultList();

        }

        public List<Category> footballbootPARENT_ID() {


            return em.createQuery("select o from Category o  where o.category='' ", Category.class)
                    .getResultList();

        }


    }
}





